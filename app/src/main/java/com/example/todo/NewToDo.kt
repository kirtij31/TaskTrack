package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class NewToDo : Fragment() {

    private lateinit var editText : EditText
    lateinit var auth: FirebaseAuth
    lateinit var toDoDao: ToDoDao


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_new_to_do, container, false)

        editText = view.findViewById(R.id.newText) as EditText

        auth = FirebaseAuth.getInstance()

        return view
    }


    override fun onPause() {
        super.onPause()
        toDoDao = ToDoDao()
        val text = editText.text.toString().trim()
        toDoDao.addToDo(text)
    }


}