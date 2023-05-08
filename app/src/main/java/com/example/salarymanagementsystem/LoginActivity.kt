package com.example.salarymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var login: Button
    lateinit var email: EditText
    lateinit var password : EditText
    //lateinit var forget:TextView
    private val predefinedEmail = "admin"
    private val predefinedPassword = "123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = findViewById(R.id.login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.pass)
//        forget = findViewById(R.id.forgetPass)

        login.setOnClickListener {
            val enteredEmail = email.text.toString()
            val enteredPassword = password.text.toString()

            // Check if entered email and password match predefined email and password
            if (enteredEmail == predefinedEmail && enteredPassword == predefinedPassword) {
                // Navigate to next activity using intent
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Show toast indicating incorrect username or password
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }
//        forget.setOnClickListener {
////            // Show toast indicating that this feature is not available
////            Toast.makeText(this, "Forgot password feature is not available", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Forgetpass::class.java)
//            startActivity(intent)
//            finish()
//        }

    }
}