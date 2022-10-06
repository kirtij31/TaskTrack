package com.example.todo.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.models.ToDoModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ToDoAdapter(options: FirestoreRecyclerOptions<ToDoModel>) : FirestoreRecyclerAdapter<ToDoModel, ToDoAdapter.ToDoViewHolder>(
    options
) {

    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val toDoText : TextView = itemView.findViewById(R.id.toDoText)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        val linearLayout:LinearLayout = itemView.findViewById(R.id.itemLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_item,parent,false)
        val viewHolder = ToDoViewHolder(view)

        viewHolder.checkBox.setOnClickListener {
            val checked = viewHolder.checkBox.isChecked

            if(checked){
                viewHolder.linearLayout.setBackgroundColor(Color.LTGRAY)
            }
            else{
                viewHolder.linearLayout.setBackgroundColor(Color.WHITE)
            }

        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int, model: ToDoModel) {
        holder.toDoText.text = model.text
    }

}