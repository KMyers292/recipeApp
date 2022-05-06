package com.example.recipeapp.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "recipe.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "items";
    public static final String ID = "_id";
    public static final String ITEM = "item";
    public static final String COMPLETED = "completed";

    private static DatabaseHelper instance = null;

//------------------------------------------------------------------------------------------------//

    public static DatabaseHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

//------------------------------------------------------------------------------------------------//

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//------------------------------------------------------------------------------------------------//

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " item TEXT NOT NULL," +
                " completed INTEGER NOT NULL DEFAULT 0)";

        sqLiteDatabase.execSQL(createQuery);
    }

//------------------------------------------------------------------------------------------------//

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { }
}