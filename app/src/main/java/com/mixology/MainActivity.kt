package com.mixology

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mixology.adapters.RecipeAdapter
import com.mixology.common.getJSONAsset
import com.mixology.databinding.ActivityMainBinding
import com.mixology.models.Recipe
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var recipeList = ArrayList<Recipe>()
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Sets up initial recipe list (mocked for now)
        val gson = Gson()
        val jsonStr = getJSONAsset(this, "recipes.json")
        val type = object: TypeToken<List<Recipe>>() {}.type
        recipeList = gson.fromJson(jsonStr, type)


        // Sets up recipeList adapter
        val adapter = RecipeAdapter(this, recipeList)
        listView = findViewById(R.id.recipe_list)
        listView.adapter = adapter

        // Sets click event for fab button
        binding.fab.setOnClickListener { view ->
            val recipe = Recipe("Recipe", Random.nextInt().toString(), recipeList[0].image)
            recipeList.add(recipe)
            adapter.notifyDataSetChanged()

            val myToast = Toast.makeText(this, "New Recipe Added!", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }
}