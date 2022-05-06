package com.example.recipeapp.recipe_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.recipeapp.R;
import com.example.recipeapp.shopping_list.ShoppingList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton shoppingListButton = findViewById(R.id.shopping_list_logo);
        shoppingListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ShoppingList.class));
            }
        });

        ImageView breakfastImage = findViewById(R.id.breakfastImage);
        breakfastImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, BreakfastRecipes.class));
            }
        });

        ImageView lunchImage = findViewById(R.id.lunchImage);
        lunchImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, LunchRecipes.class));
            }
        });

        ImageView dinnerImage = findViewById(R.id.dinnerImage);
        dinnerImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, DinnerRecipes.class));
            }
        });

        ImageView dessertImage = findViewById(R.id.dessertImage);
        dessertImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, DessertRecipes.class));
            }
        });

        ImageView drinksImage = findViewById(R.id.drinksImage);
        drinksImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, DrinksRecipes.class));
            }
        });

        ImageView snacksImage = findViewById(R.id.snacksImage);
        snacksImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SnacksRecipes.class));
            }
        });

        ImageView otherImage = findViewById(R.id.otherImage);
        otherImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, OtherRecipes.class));
            }
        });

        ImageView allImage = findViewById(R.id.allImage);
        allImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, AllRecipes.class));
            }
        });
    }
}