package com.example.playstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(private val apps: List<App>) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appName: TextView = view.findViewById(R.id.appName)
        val appDescription: TextView = view.findViewById(R.id.appDescription)
        val appSize: TextView = view.findViewById(R.id.appSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.appName.text = app.name
        holder.appDescription.text = app.description
        holder.appSize.text = app.size
    }

    override fun getItemCount() = apps.size
}
