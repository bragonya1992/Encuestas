package com.apps.brayan.surveyapp.surveychooser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.apps.brayan.surveyapp.R
import com.apps.brayan.surveyapp.SurveyScreen
import com.apps.brayan.surveyapp.coreApp.SurveyConstants
import com.apps.brayan.surveyapp.models.Survey
import kotlinx.android.synthetic.main.activity_survey_chooser.*

class SurveyChooser : AppCompatActivity(), SCClick {

    lateinit var model:SCViewModel
    lateinit var adapter: SCAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_chooser)
        model = ViewModelProviders.of(this).get(SCViewModel::class.java)
        setupRecyclerView()
        fetchData()
    }

    fun setupRecyclerView(){
        recyclerSurveyChooser.layoutManager = LinearLayoutManager(this)
        adapter =  SCAdapter(ArrayList(), this,this)
        recyclerSurveyChooser.adapter = adapter
    }

    fun fetchData(){
        model.getSurveys().observe(this, Observer {
            showNewDataSet(it)
        })
        model.fetchAllSurveys()
    }

    fun showNewDataSet(list: ArrayList<Survey>?){
        if(list!=null){
            adapter.replaceItems(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(item: Survey) {
        val intent = Intent(this,SurveyScreen::class.java)
        intent.putExtra(SurveyConstants.SURVEY_BODY_INTENT,item.body)
        startActivity(intent)
    }
}
