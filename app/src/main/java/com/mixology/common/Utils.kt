package com.mixology.common

import android.content.Context
import java.io.IOException

fun getJSONAsset(context: Context, fileName: String): String? {
    var json: String = ""

    try {
        json = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return json
}