package com.example.playstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val categories: List<Category>) : 
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryTitle: TextView = view.findViewById(R.id.categoryTitle)
        val horizontalRecyclerView: RecyclerView = view.findViewById(R.id.horizontalRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTitle.text = category.title
        
        // Set up horizontal RecyclerView
        holder.horizontalRecyclerView.layoutManager = 
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.horizontalRecyclerView.adapter = AppAdapter(category.apps)
    }

    override fun getItemCount() = categories.size
}
