package com.mixology

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mixology.adapters.IngredientListAdapter
import com.mixology.databinding.IngredientListFragmentBinding
import com.mixology.models.Ingredient
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class IngredientListFragment : Fragment() {
    private var _binding: IngredientListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var appContext: Context
    private var ingredientList = ArrayList<Ingredient>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = IngredientListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        appContext = requireContext()

        val adapter = IngredientListAdapter(appContext, ingredientList)
        binding.ingredientList.adapter = adapter

        for (i in 0..10) {
            ingredientList.add(Ingredient("Ingredient ${Random.nextInt()}"))
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}