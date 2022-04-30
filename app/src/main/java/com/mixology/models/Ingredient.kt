package com.mixology.models

class Ingredient(ingredientName: String, ingredientAmount: String = "") {
    var name: String = ingredientName
    var amount: String = ingredientAmount

    override fun toString(): String {
        return "$name: $amount"
    }
}