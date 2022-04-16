package com.mixology.models

class Recipe(recipeTitle: String, recipeDesc: String, recipeSrc: String) {
    var title: String = recipeTitle
    var description: String = recipeDesc
    var image: String = recipeSrc

    override fun toString(): String {
        return title
    }
}