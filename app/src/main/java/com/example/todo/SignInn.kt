package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.daos.UserDao
import com.example.todo.models.UserModel
import com.google.firebase.auth.FirebaseAuth

class SignInn : Fragment() {


    lateinit var auth: FirebaseAuth

    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var name:EditText
    lateinit var age:EditText
    lateinit var dob:EditText
    lateinit var logIn:Button
    lateinit var signUp:Button
    lateinit var newUser:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_inn, container, false)


         email = view.findViewById(R.id.email)
         password = view.findViewById(R.id.password)
         name = view.findViewById(R.id.newName)
         age = view.findViewById(R.id.newAge)
         dob = view.findViewById(R.id.newDob)
         logIn = view.findViewById(R.id.logIn)
         signUp = view.findViewById(R.id.signUp)
         newUser = view.findViewById(R.id.newUser)

        auth = FirebaseAuth.getInstance()

        newUser.setOnClickListener {
            name.visibility = View.VISIBLE
            age.visibility = View.VISIBLE
            dob.visibility = View.VISIBLE
            signUp.visibility = View.VISIBLE
        }

        signUp.setOnClickListener {
           userSignUp()
        }

        logIn.setOnClickListener {
           userLogIn()
        }
        return view
    }

    private fun userSignUp(){
        val emailText = email.text.toString().trim()
        val passwordText = password.text.toString().trim()

        val name = name.text.toString().trim()
        val age = age.text.toString().trim()
        val dob = dob.text.toString().trim()

        if(name.isNotEmpty() && age.isNotEmpty() && dob.isNotEmpty()){
            auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser!!

                        Toast.makeText(context, "SignIn succeeded.", Toast.LENGTH_SHORT).show()

                            val user = UserModel(
                                currentUser.uid,
                                name,
                                "",
                                age,
                                dob
                            )
                            val dao = UserDao()
                            dao.addUser(user)


                    } else {
                        Toast.makeText(context, "SignIn failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            Toast.makeText(context,"Enter All Details",Toast.LENGTH_SHORT).show()
        }
    }


    private fun userLogIn(){
        auth.signInWithEmailAndPassword(
            email.text.toString().trim(),
            password.text.toString().trim()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Authentication succeeded.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.allToDo)


            }
            else {
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        val currentUser = auth.currentUser
        if(currentUser!=null)
            findNavController().navigate(R.id.allToDo)
        super.onStart()
    }

}