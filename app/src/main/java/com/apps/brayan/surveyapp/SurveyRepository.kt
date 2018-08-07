package com.apps.brayan.surveyapp

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.apps.brayan.surveyapp.models.Survey
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class SurveyRepository() {
    var myRef:DatabaseReference

    fun fetchSurveys(liveData:MutableLiveData<ArrayList<Survey>>){
        val surveyListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listSurveys = ArrayList<Survey>()
                for (data:DataSnapshot in dataSnapshot.children){
                    listSurveys.add(Survey(data.child("nombre").value.toString(),data.child("encuesta").value.toString(),data.key))
                }
                liveData.postValue(listSurveys)
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        myRef.addListenerForSingleValueEvent(surveyListener)
    }

    init {
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReferenceFromUrl("https://bdsurvey-4d97c.firebaseio.com/proyectos/organizacionheifer/encuestas")
    }


}