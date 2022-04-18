package com.mixology.models

class Recipe(recipeId: String, recipeTitle: String, recipeDesc: String, recipeSrc: String) {
    var id: String = recipeId
    var title: String = recipeTitle
    var description: String = recipeDesc
    var image: String = recipeSrc

    override fun toString(): String {
        return "$id: $title"
    }
}