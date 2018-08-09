package com.apps.brayan.surveyapp.coreApp

import android.content.Context
import android.preference.PreferenceManager
import com.apps.brayan.surveyapp.models.User
import com.google.gson.Gson

class SessionManager {
    companion object {
        val KEY_SESSION = "KEY_SESSION"
        var instance:User? = null

        fun getActualUser(context: Context):User?{
            val userString = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_SESSION,"")
            if(instance == null && !userString.isEmpty()) {
                val gson = Gson()
                instance = gson.fromJson<User>(userString, User::class.java)
            }
            return instance
        }

        fun createUser(context: Context,user: User){
            instance = user
            val gson = Gson()
            val userString = gson.toJson(user)
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_SESSION,userString).apply()
        }

        fun logOut(context: Context){
            instance=null
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_SESSION,"").apply()
        }
    }
}