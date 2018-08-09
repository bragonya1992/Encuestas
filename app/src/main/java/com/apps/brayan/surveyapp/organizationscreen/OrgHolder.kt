package com.apps.brayan.surveyapp.organizationscreen

import android.support.v7.widget.RecyclerView
import android.view.View
import com.apps.brayan.surveyapp.models.Survey
import com.apps.brayan.surveyapp.surveychooser.SCClick
import kotlinx.android.synthetic.main.survey_chooser_holder.view.*

class OrgHolder (itemView: View?, var listener: OrgClick) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, item: String) {
        itemView.surveyName.text = item
        itemView.setOnClickListener({ listener.onClick(item) })
    }
}