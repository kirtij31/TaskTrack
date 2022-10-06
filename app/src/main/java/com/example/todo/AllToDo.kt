package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.adapters.ToDoAdapter
import com.example.todo.daos.ToDoDao
import com.example.todo.models.ToDoModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class AllToDo : Fragment() {


     private lateinit var adapter: ToDoAdapter

     private lateinit var auth: FirebaseAuth

    private val toDoDao = ToDoDao()

    private val collection : CollectionReference = toDoDao.toDoCollection

    lateinit var query : Query
    lateinit var recyclerOptions: FirestoreRecyclerOptions<ToDoModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_all_to_do, container, false)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser!!
        query = collection
            .orderBy("timestamp", Query.Direction.DESCENDING)



        recyclerOptions = FirestoreRecyclerOptions.Builder<ToDoModel>().setQuery(query,
            ToDoModel::class.java).build()
        adapter= ToDoAdapter(recyclerOptions)


        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LayoutManagerWrapper(context,LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = adapter



        val fab = view.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener{
            val bottomSheetFragment = BottomSheetNewToDoFragment()
            bottomSheetFragment.show(childFragmentManager,bottomSheetFragment.tag)
        }


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
