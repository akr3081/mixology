package com.mixology.models

class Recipe(recipeId: String, recipeTitle: String, recipeDesc: String, recipeSrc: String, recipeIngredients: ArrayList<Ingredient>) {
    var id: String = recipeId
    var title: String = recipeTitle
    var description: String = recipeDesc
    var image: String = recipeSrc
    var ingredients: ArrayList<Ingredient> = recipeIngredients

    override fun toString(): String {
        return "$id: $title"
    }
}