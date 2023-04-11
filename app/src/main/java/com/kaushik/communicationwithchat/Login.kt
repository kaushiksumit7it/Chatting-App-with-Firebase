package com.kaushik.communicationwithchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edt_email: EditText
    private lateinit var edt_pass: EditText
    private lateinit var btn_login: Button
    private lateinit var btn_signup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edt_email = findViewById(R.id.edt_email)
        edt_pass = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_pass.text.toString()

            login(email, password)

        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success

                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user
                    if (email.isEmpty()) {
                        Toast.makeText(this@Login, "Email Empty!!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Login, "Some error occurred!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}