package com.mixology.common

import android.content.Context
import com.mixology.models.Recipe
import org.json.JSONObject
import java.io.IOException
import java.lang.Math.min
import java.net.URL

/**
 * Loads json string from asset filename
 * @param context - Context of the application where assets file is pulled from
 * @param fileName - Path of the assets file
 */
fun getJSONAsset(context: Context, fileName: String): String? {
    var json: String = ""

    try {
        json = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return json
}

/**
 * Gets recipe list from thecocktaildb
 * @param filter - The filter string being applied to get call
 * @param count - Optional count param for how many result to be returned
 */
fun getRecipeList(filter: String, count: Int? = null): ArrayList<Recipe> {
    val recipes = ArrayList<Recipe>()

    val res = URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?$filter").readText()
    val recipeArr = JSONObject(res).getJSONArray("drinks")

    val recipeCount = if(count != null) min(recipeArr.length(), count) else recipeArr.length()

    for(i in 0 until recipeCount) {
        val recipeObj = recipeArr.getJSONObject(i)

        val title = recipeObj.getString("strDrink")
        val description = recipeObj.getString("strInstructions")
        val image = recipeObj.getString("strDrinkThumb")

        val r = Recipe(title, description, image)
        recipes.add(r)
    }

    return recipes
}