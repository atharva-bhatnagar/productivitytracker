package com.example.salarymanagementsystem

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity


class RatingsFragment : Fragment(R.layout.fragment_ratings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitButton = view.findViewById<View>(R.id.button)
        submitButton.setOnClickListener {
            val intent = Intent(activity, Rating2::class.java)
            startActivity(intent)

        }
    }



}