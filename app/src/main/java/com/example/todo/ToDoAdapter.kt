package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ToDoAdapter(options: FirestoreRecyclerOptions<ToDoModel>) : FirestoreRecyclerAdapter<ToDoModel, ToDoAdapter.ToDoViewHolder>(
    options
) {

    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val toDoText : TextView = itemView.findViewById(R.id.toDoText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_item,parent,false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int, model: ToDoModel) {
        holder.toDoText.text = model.text
    }


}