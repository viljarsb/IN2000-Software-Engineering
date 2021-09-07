package com.example.team39_prosjekt.data.userDataPersistentStorage

import android.app.Activity
import android.content.Context


//Stores user data about favourised beaches.
object Userdata {
    private const val FAVOURITES = "favourites"

    fun toggleFavourite(name : String, activity : Activity): Boolean {
        val sharedPreferences = activity.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var ret = false
        if (sharedPreferences.getBoolean(name, false)) {
            editor.putBoolean(name, false)
        } else {
            editor.putBoolean(name, true)
            ret = true
        }
        editor.apply()
        return ret
    }

    //Controls if a beach favourised.
    fun isFavourite(name: String, activity : Activity): Boolean {
        val sharedPref = activity.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(name, false)
    }
}