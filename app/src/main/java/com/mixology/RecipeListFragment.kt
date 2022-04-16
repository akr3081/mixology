package com.mixology

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mixology.adapters.RecipeAdapter
import com.mixology.common.getJSONAsset
import com.mixology.databinding.RecipeListFragmentBinding
import com.mixology.models.Recipe
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment : Fragment() {
    private var _binding: RecipeListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var appContext: Context

    private var recipeList = ArrayList<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        println("FRAGMENT onCreateView.................")
        _binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appContext = requireContext()

        // Sets up initial recipe list (mocked for now)
        val gson = Gson()
        val jsonStr = getJSONAsset(appContext, "recipes.json")
        val type = object: TypeToken<List<Recipe>>() {}.type
        recipeList = gson.fromJson(jsonStr, type)

        val adapter = RecipeAdapter(appContext, recipeList)
        binding.recipeList.adapter = adapter

        // Navigates to recipeDetail fragment when item is clicked
        binding.recipeList.setOnItemClickListener { parent, _, position, _ ->
            findNavController().navigate(R.id.action_navigate_to_RecipeDetailFragment)
        }

        // Adds new recipe to the recipeList when fab is clicked
        binding.fab.setOnClickListener { view ->
            val imageUrl = "https://picsum.photos/" + (0..1000).random()
            val recipe = Recipe("Recipe" + Random.nextInt().toString(), "Description", imageUrl)
            recipeList.add(recipe)
            adapter.notifyDataSetChanged()

            val myToast = Toast.makeText(appContext, "Recipe List Updated!", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}