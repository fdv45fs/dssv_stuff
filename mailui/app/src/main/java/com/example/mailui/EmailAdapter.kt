package com.example.mailui

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: List<Email>) : 
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAvatar: TextView = view.findViewById(R.id.tvAvatar)
        val tvSenderName: TextView = view.findViewById(R.id.tvSenderName)
        val tvSubject: TextView = view.findViewById(R.id.tvSubject)
        val tvPreview: TextView = view.findViewById(R.id.tvPreview)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        
        holder.tvAvatar.text = email.senderName.first().uppercase()
        holder.tvSenderName.text = email.senderName
        holder.tvSubject.text = email.subject
        holder.tvPreview.text = email.preview
        holder.tvTime.text = email.time
    }

    override fun getItemCount() = emails.size
}
