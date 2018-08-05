package com.apps.brayan.surveyapp

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class SurveyRepository {

    constructor(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
    }

    fun getAllSurveys(){

    }
}