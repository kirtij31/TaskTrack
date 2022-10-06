package com.example.todo.daos

import com.example.todo.models.ToDoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ToDoDao {

    private val dao = FirebaseFirestore.getInstance()
    val toDoCollection = dao.collection("toDos")

    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser!!


    fun addToDo(text:String){
        GlobalScope.launch (Dispatchers.IO){
            val currentTime = System.currentTimeMillis()
            val toDoModel = ToDoModel(currentUser.uid,text,currentTime)
            toDoCollection.document().set(toDoModel)
        }
    }

}