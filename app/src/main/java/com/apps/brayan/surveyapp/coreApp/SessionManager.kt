package com.apps.brayan.surveyapp.coreApp

import android.content.Context
import android.preference.PreferenceManager
import com.apps.brayan.surveyapp.models.Survey
import com.apps.brayan.surveyapp.models.User
import com.google.gson.Gson
import android.net.NetworkInfo
import android.net.ConnectivityManager
import com.google.gson.reflect.TypeToken


class SessionManager {
    companion object {
        val KEY_SESSION = "KEY_SESSION"
        var userInstance:User? = null

        fun getActualUser(context: Context):User?{
            val userString = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_SESSION,"")
            if(userInstance == null && !userString.isEmpty()) {
                val gson = Gson()
                userInstance = gson.fromJson<User>(userString, User::class.java)
            }
            return userInstance
        }

        fun createUser(context: Context,user: User){
            userInstance = user
            val gson = Gson()
            val userString = gson.toJson(user)
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_SESSION,userString).apply()
        }

        fun logOut(context: Context){
            userInstance=null
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_SESSION,"").apply()
        }

        fun generateSurveysCacheByOrganization(context: Context,list:ArrayList<Survey>,keyOrg:String){
            val gson = Gson()
            val surveyString = gson.toJson(list)
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(keyOrg,surveyString).apply()
        }

        fun getCacheByOrganization(context: Context,keyOrg:String):ArrayList<Survey>?{
            val cache = PreferenceManager.getDefaultSharedPreferences(context).getString(keyOrg,"")
            var cacheInstance:ArrayList<Survey>? =null
            if(!cache.isEmpty()) {
                val gson = Gson()
                val type = object : TypeToken<ArrayList<Survey>>() {}.type
                cacheInstance = gson.fromJson<ArrayList<Survey>>(cache, type)
            }
            return cacheInstance
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}