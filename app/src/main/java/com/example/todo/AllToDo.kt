package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class AllToDo : Fragment() {


    private lateinit var adapter: ToDoAdapter

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_all_to_do, container, false)

        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        val fab = view.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener{
            findNavController().navigate(R.id.newToDo)
        }

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser!!

        val toDoDao = ToDoDao()
        val collection = toDoDao.toDoCollection.whereEqualTo("uid" , user.uid )
        val recyclerOptions = FirestoreRecyclerOptions.Builder<ToDoModel>().setQuery(collection,ToDoModel::class.java).build()

        adapter = ToDoAdapter(recyclerOptions)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        return view
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
