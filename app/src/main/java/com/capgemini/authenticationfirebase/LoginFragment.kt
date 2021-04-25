package com.capgemini.authenticationfirebase

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //---AUTH INSTANCE---
        auth = FirebaseAuth.getInstance()
        loginB.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if(loginEmailE.text.isEmpty()){
            loginEmailE.error ="Please enter email"
            loginEmailE.requestFocus()
            return
        }
        if(loginPasswordE.text.isEmpty()){
            loginPasswordE.error ="Please enter password"
            loginPasswordE.requestFocus()
            return
        }

        //----LOGIN USER---
        auth.signInWithEmailAndPassword(loginEmailE.text.toString(), loginPasswordE.text.toString())
            .addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else { //wrong details
                    Toast.makeText(activity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }

    private fun updateUI(currentUser : FirebaseUser?){
        startActivity(Intent(activity,MainActivity2::class.java))
        Toast.makeText(activity, "Authentication Succesful.",
                Toast.LENGTH_SHORT).show()
    }

/*if user exists
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }*/





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}