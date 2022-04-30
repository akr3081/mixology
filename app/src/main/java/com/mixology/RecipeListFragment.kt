package com.mixology

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.mixology.adapters.RecipeListAdapter
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        appContext = requireContext()

        val adapter = RecipeListAdapter(appContext, recipeList)
        binding.recipeList.adapter = adapter

        // Navigates to recipeDetail fragment when item is clicked
        binding.recipeList.setOnItemClickListener { parent, _, position, _ ->
            setFragmentResult("recipeId", bundleOf("recipeId" to recipeList[position].id))
            findNavController().navigate(R.id.action_navigate_to_RecipeDetailFragment)
        }

        // Adds new recipe to the recipeList when fab is clicked
        binding.fab.setOnClickListener { view ->
            val letter = (('a'..'z')).random()
            val results = getRecipeList("f=$letter")
            recipeList.clear()
            recipeList.addAll(results)
            adapter.notifyDataSetChanged()

            val count = recipeList.size.toString()
            val myToast = Toast.makeText(appContext, "Fetched $count Recipes", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}