package com.example.salarymanagementsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView


class HomeFragment : Fragment() {
    lateinit var add:CardView
    lateinit var rate:CardView
    lateinit var about:CardView
    lateinit var calc:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private fun loadFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.homeLayout, fragment)
            ?.commit()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the TextView in the fragment's layout file
        val textView = view.findViewById<TextView>(R.id.home)
        add=view.findViewById(R.id.add)
        rate=view.findViewById(R.id.rate)
        about=view.findViewById(R.id.about)
        calc=view.findViewById(R.id.calc)

        add.setOnClickListener(View.OnClickListener {
            loadFragment(AddEmp_Fragment())
        })
        rate.setOnClickListener(View.OnClickListener {
            loadFragment(RatingsFragment())
        })
        calc.setOnClickListener(View.OnClickListener {
            loadFragment(SalaryFragment())
        })
        about.setOnClickListener(View.OnClickListener {
            loadFragment(AboutUsFragment())
        })

        // Set the text of the TextView

    }
}