package com.devjcastro.androidtestmovies.utils

class PreferencesUtils {

    object KeyPreferences {
        val PREFERENCE_NAME = "applicationPreferences"
        val CATEGORY_ID = "categoryId"
        val CATEGORY_NAME = "categoryName"
        val API_KEY = "apiKey"

        val FILM_ID = "filmId"
    }

    companion object {

        fun updateApiKey(apiKey: String) {
            Prefs.putString(KeyPreferences.API_KEY, apiKey)
        }
    }

    fun clone(): PreferencesUtils? {
        try {
            throw CloneNotSupportedException()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }

        return null
    }
}