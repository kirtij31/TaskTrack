package com.example.todo.daos

import com.example.todo.models.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {


    private val dao = FirebaseFirestore.getInstance()
     private val userCollection = dao.collection("users")


    fun addUser(user: UserModel){
            GlobalScope.launch(Dispatchers.IO) {
                userCollection.document(user.uid).set(user)}
    }

      fun getUserById(uid:String):Task<DocumentSnapshot>{
        return userCollection.document(uid).get()
    }

}
