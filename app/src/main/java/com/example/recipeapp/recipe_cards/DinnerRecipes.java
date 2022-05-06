package com.example.recipeapp.recipe_cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.recipeapp.R;
import com.example.recipeapp.shopping_list.ShoppingList;

import java.util.List;

public class DinnerRecipes extends AppCompatActivity
{
    private RecipeListManager listManager;
    private RecipeListItemAdapter adapter;

    private String[] categories = {"Breakfast", "Lunch", "Dinner", "Dessert", "Drinks", "Snacks",
            "Other"};

    private String[] dinner = {"Dinner"};

//------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_recipes);

        ImageButton recipesButton = findViewById(R.id.recipe_logo);
        recipesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DinnerRecipes.this, MainActivity.class));
            }
        });

        ImageButton shoppingListButton = findViewById(R.id.shopping_list_logo);
        shoppingListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DinnerRecipes.this, ShoppingList.class));
            }
        });

        ListView recipeList = findViewById(R.id.recipe_list);
        ImageButton addButton = findViewById(R.id.add_recipe);

        listManager = new RecipeListManager(this);
        adapter = new RecipeListItemAdapter(this, listManager.getRecipes(dinner));
        recipeList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onAddButtonClick();
            }
        });
    }

//------------------------------------------------------------------------------------------------//

    private void onAddButtonClick()
    {
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Recipe");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText title = new EditText(this);
        title.setHint("Recipe Name");
        layout.addView(title);

        final Spinner spinner = new Spinner(this);
        spinner.setPrompt("Recipe Category");
        spinner.setAdapter(spinnerAdapter);
        layout.addView(spinner);

        final EditText time = new EditText(this);
        time.setHint("Total Time");
        layout.addView(time);

        final EditText ingredients = new EditText(this);
        ingredients.setHint("Ingredients");
        layout.addView(ingredients);

        final EditText directions = new EditText(this);
        directions.setHint("Directions");
        layout.addView(directions);

        builder.setView(layout);

        builder.setNegativeButton(
                android.R.string.cancel,
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                    }
                }
        );

        builder.setPositiveButton(
                android.R.string.ok,
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        RecipeListItem item = new RecipeListItem(
                                title.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                time.getText().toString(),
                                ingredients.getText().toString(),
                                directions.getText().toString()
                        );

                        listManager.addItem(item);
                        adapter.updateItems(listManager.getRecipes(dinner));
                    }
                }
        );

        builder.show();
    }

//------------------------------------------------------------------------------------------------//

    private class RecipeListItemAdapter extends ArrayAdapter<RecipeListItem>
    {
        private Context context;
        private List<RecipeListItem> items;

     //-------------------------------------------------------------------------------------------//

        public RecipeListItemAdapter(
                Context context,
                List<RecipeListItem> items)
        {
            super(context, -1, items);

            this.context = context;
            this.items = items;
        }

     //-------------------------------------------------------------------------------------------//

        public void updateItems(List<RecipeListItem> items)
        {
            this.items = items;
            notifyDataSetChanged();
        }

     //-------------------------------------------------------------------------------------------//

        @Override
        public int getCount()
        {
            return items.size();
        }

     //-------------------------------------------------------------------------------------------//

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent)
        {
            final ItemViewHolder holder;

            if(convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.recipe_list_item_layout,
                        parent,
                        false
                );

                holder = new ItemViewHolder();
                holder.name = convertView.findViewById(R.id.titleTextView);
                holder.category = convertView.findViewById(R.id.categoryTextView);
                holder.time = convertView.findViewById(R.id.timeTextView);
                holder.ingredients = convertView.findViewById(R.id.ingredientsTextView);
                holder.directions = convertView.findViewById(R.id.directionsTextView);

                convertView.setTag(holder);
            }
            else
            {
                holder = (ItemViewHolder) convertView.getTag();
            }

            holder.name.setText(items.get(position).getName());
            holder.category.setText(items.get(position).getCategory());
            holder.time.setText(items.get(position).getTime());
            holder.ingredients.setText(items.get(position).getIngredients());
            holder.directions.setText(items.get(position).getDirections());

            holder.name.setTag(items.get(position));

            ImageButton deleteButton = convertView.findViewById(R.id.delete_item);

            deleteButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    RecipeListItem item = (RecipeListItem) holder.name.getTag();

                    items.remove(items.get(position));
                    listManager.deleteItem(item);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

//------------------------------------------------------------------------------------------------//

    public static class ItemViewHolder
    {
        public TextView name;
        public TextView category;
        public TextView time;
        public TextView ingredients;
        public TextView directions;
    }
}