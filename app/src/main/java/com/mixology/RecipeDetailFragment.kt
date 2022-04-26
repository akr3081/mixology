package com.mixology

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.mixology.common.getRecipeDetails
import com.mixology.databinding.RecipeDetailFragmentBinding
import com.mixology.models.Recipe

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecipeDetailFragment : Fragment() {
    private var _binding: RecipeDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = RecipeDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("recipeId") { key, bundle ->
            val recipeId = bundle.getString("recipeId").toString()
            val recipeDetail: Recipe = getRecipeDetails(recipeId)

            binding.recipeDetailTitle.text = recipeDetail.title
            binding.recipeDetailInstructions.text = recipeDetail.description

            // Makes bulleted list of ingredients
            val builder =  SpannableStringBuilder()
            for (i in 0..recipeDetail.ingredients.size - 1) {
                var str = recipeDetail.ingredients[i].toString()
                if (i < recipeDetail.ingredients.size - 1) {
                    str += "\n"
                }
                builder.append(str, BulletSpan(15), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            binding.recipeDetailIngredients.text = builder

            Glide.with(this).load(recipeDetail.image).placeholder(R.drawable.thumbnail).into(binding.recipeDetailImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}