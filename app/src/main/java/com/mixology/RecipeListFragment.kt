package com.mixology

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mixology.adapters.RecipeAdapter
import com.mixology.common.getRecipeList
import com.mixology.databinding.RecipeListFragmentBinding
import com.mixology.models.Recipe

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
        _binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        appContext = requireContext()

        // Sets up initial recipe list (mocked for now)
        val res = getRecipeList("f=m", 2)
        recipeList.addAll(res)

        val adapter = RecipeAdapter(appContext, recipeList)
        binding.recipeList.adapter = adapter

        // Navigates to recipeDetail fragment when item is clicked
        binding.recipeList.setOnItemClickListener { parent, _, position, _ ->
            findNavController().navigate(R.id.action_navigate_to_RecipeDetailFragment)
        }

        // Adds new recipe to the recipeList when fab is clicked
        binding.fab.setOnClickListener { view ->
            val myToast = Toast.makeText(appContext, "Fetched Recipe Data", Toast.LENGTH_SHORT)
            myToast.show()

            val results = getRecipeList("f=d")
            recipeList.clear()
            recipeList.addAll(results)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}