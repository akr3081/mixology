package com.mixology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mixology.databinding.IngredientListFragmentBinding
import com.mixology.models.Ingredient

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class IngredientListFragment : Fragment() {
    private var _binding: IngredientListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = IngredientListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}