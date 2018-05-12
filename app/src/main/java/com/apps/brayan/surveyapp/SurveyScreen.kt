package com.apps.brayan.surveyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.app.Activity
import android.view.Window
import android.view.Window.FEATURE_PROGRESS
import kotlinx.android.synthetic.main.activity_survey_screen.*


class SurveyScreen : AppCompatActivity() {
    companion object {
        val domainName:String = "http://192.168.100.44:8080"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_PROGRESS)
        setContentView(R.layout.activity_survey_screen)
        setupWebView(webViewSurvey)
    }

    fun setupWebView(webview: WebView){

        webview.getSettings().setJavaScriptEnabled(true)

        val activity = this
        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                activity.setProgress(progress * 1000)
            }
        })
        webview.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(activity, "Oh no! $description", Toast.LENGTH_SHORT).show()
            }
        })

        webview.loadUrl(domainName,getHeader())
    }

    fun getHeader(): HashMap<String,String>{
        var hashMap:HashMap<String,String> = HashMap()
        hashMap["json"] = "{\"pages\":[{\"name\":\"page1\",\"elements\":[{\"type\":\"radiogroup\",\"name\":\"question1\",\"choices\":[\"item1\",\"item2\",\"item3\"]},{\"type\":\"radiogroup\",\"name\":\"question3\",\"choices\":[\"item1\",\"item2\",\"item3\"]},{\"type\":\"checkbox\",\"name\":\"question2\",\"choices\":[\"item1\",\"item2\",\"item3\"]}]}]}"
        return hashMap
    }

}
