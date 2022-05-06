package com.example.recipeapp.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "recipeList.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "recipes";
    public static final String ID = "_id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_CATEGORY = "category";
    public static final String TOTAL_TIME = "time";
    public static final String INGREDIENTS = "ingredients";
    public static final String DIRECTIONS = "directions";

    private static RecipeDatabaseHelper instance = null;

//------------------------------------------------------------------------------------------------//

    public static RecipeDatabaseHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new RecipeDatabaseHelper(context);
        }

        return instance;
    }

//------------------------------------------------------------------------------------------------//

    public RecipeDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//------------------------------------------------------------------------------------------------//

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT NOT NULL," +
                " category TEXT NOT NULL," +
                " time TEXT NOT NULL," +
                " ingredients TEXT NOT NULL," +
                " directions TEXT NOT NULL)";

        db.execSQL(createQuery);
    }

//------------------------------------------------------------------------------------------------//

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}