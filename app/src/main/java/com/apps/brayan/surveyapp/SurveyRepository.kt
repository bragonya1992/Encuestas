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
    var surveysDomain = "https://bdsurvey-4d97c.firebaseio.com/proyectos/{organization}/encuestas"


    fun fetchSurveys(liveData:MutableLiveData<ArrayList<Survey>>, organizationName:String){
        val finalUrl = surveysDomain.replace("{organization}",organizationName)
        val myRef = FirebaseDatabase.getInstance().getReferenceFromUrl(finalUrl)
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


}