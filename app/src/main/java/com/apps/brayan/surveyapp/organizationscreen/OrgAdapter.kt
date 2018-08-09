package com.apps.brayan.surveyapp.organizationscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apps.brayan.surveyapp.R

class OrgAdapter(val items : ArrayList<String>, val context: Context, var listener: OrgClick): RecyclerView.Adapter<OrgHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrgHolder {
        return OrgHolder(LayoutInflater.from(context).inflate(R.layout.survey_chooser_holder, parent, false),listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceItems(list: ArrayList<String>){
        items.clear()
        items.addAll(list)
    }

    override fun onBindViewHolder(holder: OrgHolder, position: Int) {
        holder.bind(position,items.get(position))
    }
}