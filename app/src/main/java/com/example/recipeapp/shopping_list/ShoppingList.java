package com.example.recipeapp.shopping_list;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipeapp.recipe_cards.MainActivity;
import com.example.recipeapp.R;

import java.util.List;

public class ShoppingList extends AppCompatActivity
{
    private ShoppingListManager listManager;
    private ShoppingListItemAdapter adapter;

//------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_layout);

        ImageButton recipesButton = findViewById(R.id.recipe_logo);
        recipesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ShoppingList.this, MainActivity.class));
            }
        });

        ListView shoppingList = findViewById(R.id.shopping_list);
        ImageButton addButton = findViewById(R.id.add_item);

        listManager = new ShoppingListManager(this);
        adapter = new ShoppingListItemAdapter(this, listManager.getItems());
        shoppingList.setAdapter(adapter);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Item");

        final EditText input = new EditText(this);
        builder.setView(input);

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
                        ShoppingListItem item = new ShoppingListItem(
                                input.getText().toString(),
                                false
                        );

                        listManager.addItem(item);
                        adapter.updateItems(listManager.getItems());
                    }
                }
        );

        builder.show();
    }

//------------------------------------------------------------------------------------------------//

    private class ShoppingListItemAdapter extends ArrayAdapter<ShoppingListItem>
    {
        private Context context;
        private List<ShoppingListItem> items;

     //-------------------------------------------------------------------------------------------//

        public ShoppingListItemAdapter(
                Context context,
                List<ShoppingListItem> items)
        {
            super(context, -1, items);

            this.context = context;
            this.items = items;
        }

     //-------------------------------------------------------------------------------------------//

        public void updateItems(List<ShoppingListItem> items)
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
                        R.layout.shopping_list_item_layout,
                        parent,
                        false
                );

                holder = new ItemViewHolder();
                holder.item = convertView.findViewById(R.id.itemTextView);
                holder.itemState = convertView.findViewById(R.id.completedCheckBox);

                convertView.setTag(holder);
            }
            else
                {
                    holder = (ItemViewHolder) convertView.getTag();
                }

            holder.item.setText(items.get(position).getItem());
            holder.itemState.setChecked(items.get(position).isComplete());

            holder.itemState.setTag(items.get(position));

            ImageButton deleteButton = convertView.findViewById(R.id.delete_item);

            deleteButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ShoppingListItem item = (ShoppingListItem) holder.itemState.getTag();

                    items.remove(items.get(position));
                    listManager.deleteItem(item);
                    notifyDataSetChanged();
                }
            });

            View.OnClickListener onClickListener = new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ShoppingListItem item = (ShoppingListItem) holder.itemState.getTag();
                    item.toggleComplete();
                    listManager.updateItem(item);
                    notifyDataSetChanged();
                }
            };

            convertView.setOnClickListener(onClickListener);

            holder.itemState.setOnClickListener(onClickListener);

            return convertView;
        }
    }

//------------------------------------------------------------------------------------------------//

    public static class ItemViewHolder
    {
        public TextView item;
        public CheckBox itemState;
    }
}