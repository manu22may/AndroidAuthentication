package com.capgemini.authenticationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInB.setOnClickListener {
            val frag = SignUpFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.parentL,frag)
                    .addToBackStack(null)
                    .commit()
        }

        val frag = LoginFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.parentL,frag)
                .addToBackStack(null)
                .commit()
    }
}