package com.apps.brayan.surveyapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.apps.brayan.surveyapp.coreApp.SurveyConstants
import com.apps.brayan.surveyapp.surveychooser.SurveyChooser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSecond.setOnClickListener( {goToSurveyScreen()} )
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun goToSurveyScreen(){
        var intent = Intent(this,SurveyChooser::class.java)
        intent.putExtra(SurveyConstants.SURVEY_BODY_INTENT,"{\n" +
                " pages: [\n" +
                "  {\n" +
                "   name: \"page1\",\n" +
                "   elements: [\n" +
                "    {\n" +
                "     type: \"radiogroup\",\n" +
                "     name: \"question1\",\n" +
                "     title: \"Ana me ama? si o no\",\n" +
                "     choices: [\n" +
                "      {\n" +
                "       value: \"item1\",\n" +
                "       text: \"si\"\n" +
                "      },\n" +
                "      {\n" +
                "       value: \"item2\",\n" +
                "       text: \"no\"\n" +
                "      }\n" +
                "     ]\n" +
                "    }\n" +
                "   ]\n" +
                "  }\n" +
                " ]\n" +
                "}")
        startActivity(intent)
    }


}
