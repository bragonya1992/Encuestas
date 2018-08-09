package com.apps.brayan.surveyapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.apps.brayan.surveyapp.coreApp.SessionManager
import com.apps.brayan.surveyapp.organizationscreen.OrganizationScreen
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initialFlow()
    }

    fun initialFlow(){
        logo.postDelayed(Runnable {
            val intent:Intent
            if(SessionManager.getActualUser(this) != null){
                intent = Intent(this, OrganizationScreen::class.java)
            }else{
                intent = Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        },2000)

    }
}
