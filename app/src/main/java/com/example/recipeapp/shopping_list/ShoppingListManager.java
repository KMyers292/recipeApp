package com.example.recipeapp.shopping_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipeapp.domain.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListManager
{
    private DatabaseHelper dbHelper;

//------------------------------------------------------------------------------------------------//

    public ShoppingListManager(Context context)
    {
        dbHelper = DatabaseHelper.getInstance(context);
    }

//------------------------------------------------------------------------------------------------//

    public List<ShoppingListItem> getItems()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        List<ShoppingListItem> items = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                ShoppingListItem item = new ShoppingListItem(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.ITEM)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COMPLETED))
                                != 0,
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ID))
                );

                items.add(item);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return items;
    }

//------------------------------------------------------------------------------------------------//

    public void addItem(ShoppingListItem item)
    {
        ContentValues newItem = new ContentValues();
        newItem.put(DatabaseHelper.ITEM, item.getItem());
        newItem.put(DatabaseHelper.COMPLETED, item.isComplete());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseHelper.TABLE_NAME, null, newItem);
    }

//------------------------------------------------------------------------------------------------//

    public void updateItem(ShoppingListItem item)
    {
        ContentValues updateItem = new ContentValues();
        updateItem.put(DatabaseHelper.ITEM, item.getItem());
        updateItem.put(DatabaseHelper.COMPLETED, item.isComplete());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] args = new String[] {String.valueOf(item.getId())};

        db.update(
                DatabaseHelper.TABLE_NAME,
                updateItem,
                DatabaseHelper.ID + "=?",
                args
        );
    }

//------------------------------------------------------------------------------------------------//

    public void deleteItem(ShoppingListItem item)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] args = new String[] {String.valueOf(item.getId())};

        db.delete(
                DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID + "=?",
                args
        );
    }
}