package com.mixology.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mixology.R
import com.mixology.models.Recipe

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)

        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView
        val descriptionTextView = rowView.findViewById(R.id.recipe_list_description) as TextView
        val imageView = rowView.findViewById(R.id.recipe_list_image) as ImageView

        val recipe = getItem(position) as Recipe
        titleTextView.text = recipe.title
        descriptionTextView.text = recipe.description
        Glide.with(context).load(recipe.image).placeholder(R.drawable.thumbnail).into(imageView)

        return rowView
    }
}