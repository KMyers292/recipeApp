package com.example.recipeapp.recipe_cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipeapp.domain.RecipeDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeListManager
{
    private RecipeDatabaseHelper dbHelper;

//------------------------------------------------------------------------------------------------//

    public RecipeListManager(Context context)
    {
        dbHelper = RecipeDatabaseHelper.getInstance(context);
    }

//------------------------------------------------------------------------------------------------//

    public List<RecipeListItem> getRecipes(String[] meal)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;

        if(meal == null)
        {
            cursor = db.rawQuery(
                    "SELECT * FROM " + RecipeDatabaseHelper.TABLE_NAME, null);
        }
        else
            {
                cursor = db.rawQuery(
                        "SELECT * FROM " + RecipeDatabaseHelper.TABLE_NAME + " WHERE " +
                                RecipeDatabaseHelper.RECIPE_CATEGORY + "=?", meal);
            }

        List<RecipeListItem> recipes = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                RecipeListItem recipe = new RecipeListItem(
                        cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.RECIPE_NAME)),
                        cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.RECIPE_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.TOTAL_TIME)),
                        cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.INGREDIENTS)),
                        cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.DIRECTIONS)),
                        cursor.getLong(cursor.getColumnIndex(RecipeDatabaseHelper.ID))
                );

                recipes.add(recipe);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return recipes;
    }

//------------------------------------------------------------------------------------------------//

    public void addItem(RecipeListItem recipe)
    {
        ContentValues newItem = new ContentValues();
        newItem.put(RecipeDatabaseHelper.RECIPE_NAME, recipe.getName());
        newItem.put(RecipeDatabaseHelper.RECIPE_CATEGORY, recipe.getCategory());
        newItem.put(RecipeDatabaseHelper.TOTAL_TIME, recipe.getTime());
        newItem.put(RecipeDatabaseHelper.INGREDIENTS, recipe.getIngredients());
        newItem.put(RecipeDatabaseHelper.DIRECTIONS, recipe.getDirections());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(RecipeDatabaseHelper.TABLE_NAME, null, newItem);
    }

//------------------------------------------------------------------------------------------------//

    public void deleteItem(RecipeListItem recipe)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] args = new String[] {String.valueOf(recipe.getId())};

        db.delete(
                RecipeDatabaseHelper.TABLE_NAME,
                RecipeDatabaseHelper.ID + "=?",
                args
        );
    }
}