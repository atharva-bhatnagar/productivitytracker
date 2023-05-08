package com.example.salarymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Rating2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating2)
        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener(){
             val intent = Intent(this , HomeFragment::class.java)
            startActivity(intent)
           finish()
         }
    }
}