package com.mixology.common

import com.mixology.models.Ingredient
import com.mixology.models.Recipe
import org.json.JSONObject
import java.lang.Exception
import java.lang.Math.min
import java.net.URL

/**
 * Gets a formatted list of ingredients from the recipeObj
 * @param recipeObj - JSONObject containing recipe data
 */
fun formatIngredientsList(recipeObj: JSONObject): ArrayList<Ingredient> {
    val ingredients = ArrayList<Ingredient>()

    // Converts all 15 ingredients/measures into Ingredient model and adds to list
    for (i in 1..15) {
        val name = recipeObj.getString("strIngredient$i")
        val amount = recipeObj.getString("strMeasure$i")

        // Need to check for "null" as getString will cast null to string
        val isValidName = name !== "null" && name.isNotBlank()
        val isValidAmount = amount !== "null" && amount.isNotBlank()

        if (isValidName && isValidAmount) {
            ingredients.add(Ingredient(name, amount))
        }
    }

    return ingredients
}

/**
 * Gets recipe list from thecocktaildb
 * @param filter - The filter string being applied to get call
 * @param count - Optional count param for how many result to be returned
 */
fun getRecipeList(filter: String, count: Int? = null): ArrayList<Recipe> {
    val recipes = ArrayList<Recipe>()

    try {
        val res = URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?$filter").readText()
        val recipeArr = JSONObject(res).getJSONArray("drinks")
        val recipeCount = if(count != null) min(recipeArr.length(), count) else recipeArr.length()

        for(i in 0 until recipeCount) {
            val recipeObj = recipeArr.getJSONObject(i)

            val id = recipeObj.getString("idDrink")
            val title = recipeObj.getString("strDrink")
            val description = recipeObj.getString("strInstructions")
            val image = recipeObj.getString("strDrinkThumb")
            val ingredients = formatIngredientsList(recipeObj)

            val r = Recipe(id, title, description, image, ingredients)
            recipes.add(r)
        }
    } catch(e: Exception) {
        println(e)
    }

    return recipes
}

/**
 * Gets recipe details based on id from thecocktaildb
 * @param recipeId - The id of the recipe
 */
fun getRecipeDetails(recipeId: String): Recipe {
    val res = URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=$recipeId").readText()
    val recipeObj = JSONObject(res).getJSONArray("drinks").getJSONObject(0)

    val id = recipeObj.getString("idDrink")
    val title = recipeObj.getString("strDrink")
    val description = recipeObj.getString("strInstructions")
    val image = recipeObj.getString("strDrinkThumb")
    val ingredients = formatIngredientsList(recipeObj)

    return Recipe(id, title, description, image, ingredients)
}

/**
 * Gets ingredients list from thecocktaildb
 * @param sortType - Optional param for how to sort the results
 * @param count - Optional count param for how many result to be returned
 */
fun getIngredientsList(sortType: String = "", count: Int? = null): ArrayList<Ingredient> {
    val ingredients = ArrayList<Ingredient>()

    try {
        val res = URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list").readText()
        val ingredientArr = JSONObject(res).getJSONArray("drinks")
        val ingredientCount = if(count != null) min(ingredientArr.length(), count) else ingredientArr.length()

        for(i in 0 until ingredientCount) {
            val ingredientObj = ingredientArr.getJSONObject(i)
            val name = ingredientObj.getString("strIngredient1")
            ingredients.add(Ingredient(name))
        }

        // Sorts results based on sortType
        when (sortType) {
            "ASC" -> {
                ingredients.sortBy { i -> i.name }
            }
            "DESC" -> {
                ingredients.sortByDescending { i -> i.name }
            }
        }
    } catch(e: Exception) {
        println(e)
    }

    return ingredients
}