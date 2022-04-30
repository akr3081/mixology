package com.mixology.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.mixology.R
import com.mixology.models.Ingredient
import kotlin.random.Random

class IngredientListAdapter(private val context: Context,
                            private val dataSource: ArrayList<Ingredient>) : BaseAdapter() {

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
        val rowView = inflater.inflate(R.layout.list_item_ingredient, parent, false)

        val nameTextView = rowView.findViewById(R.id.ingredient_name) as TextView
        val checkbox = rowView.findViewById(R.id.ingredient_checkbox) as CheckBox

        val ingredient = getItem(position) as Ingredient
        nameTextView.text = ingredient.name
        checkbox.isChecked = Random.nextBoolean() // TODO set up persisted state for if item is owned

        return rowView
    }
}