package com.apps.brayan.surveyapp.models

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
class SurveyResponse {

    var date: String?=null
    var body: String?=null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(date: String, body: String) {
        this.body = body
        this.date = date
    }

}