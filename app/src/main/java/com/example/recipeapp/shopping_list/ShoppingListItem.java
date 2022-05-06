package com.example.recipeapp.shopping_list;

public class ShoppingListItem
{
    private String item;
    private boolean isComplete;
    private long id;

//------------------------------------------------------------------------------------------------//

    public ShoppingListItem(String item, boolean isComplete)
    {
        this.item = item;
        this.isComplete = isComplete;
    }

//------------------------------------------------------------------------------------------------//

    public ShoppingListItem(String item, boolean isComplete, long id)
    {
        this.item = item;
        this.isComplete = isComplete;
        this.id = id;
    }

//------------------------------------------------------------------------------------------------//

    public String getItem()
    {
        return item;
    }

//------------------------------------------------------------------------------------------------//

    public boolean isComplete()
    {
        return isComplete;
    }

//------------------------------------------------------------------------------------------------//

    public void toggleComplete()
    {
        isComplete = !isComplete;
    }

//------------------------------------------------------------------------------------------------//

    public long getId()
    {
        return id;
    }
}