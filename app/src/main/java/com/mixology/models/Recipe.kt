package com.mixology.models

class Recipe(recipeTitle: String, recipeDesc: String) {
    val title: String
    var desc: String

    init {
        title = recipeTitle
        desc = recipeDesc
    }
}