package com.example.recipeapp.recipe_cards;

public class RecipeListItem
{
    private String name;
    private String category;
    private String time;
    private String ingredients;
    private String directions;
    private long id;

//------------------------------------------------------------------------------------------------//

    public RecipeListItem(String name, String category, String time, String ingredients,
                          String directions)
    {
        this.name = name;
        this.category = category;
        this.time = time;
        this.ingredients = ingredients;
        this.directions = directions;
    }

//------------------------------------------------------------------------------------------------//

    public RecipeListItem(String name, String category, String time, String ingredients,
                          String directions, long id)
    {
        this.name = name;
        this.category = category;
        this.time = time;
        this.ingredients = ingredients;
        this.directions = directions;
        this.id = id;
    }

//------------------------------------------------------------------------------------------------//

    public String getName()
    {
        return name;
    }

//------------------------------------------------------------------------------------------------//

    public String getCategory()
    {
        return category;
    }

//------------------------------------------------------------------------------------------------//

    public String getTime()
    {
        return time;
    }

//------------------------------------------------------------------------------------------------//

    public String getIngredients()
    {
        return ingredients;
    }

//------------------------------------------------------------------------------------------------//

    public String getDirections()
    {
        return directions;
    }

//------------------------------------------------------------------------------------------------//

    public long getId()
    {
        return id;
    }
}