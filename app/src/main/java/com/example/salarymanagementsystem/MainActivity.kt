package com.example.salarymanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var bottomNavigationView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        loadFragment(HomeFragment())
    }
        private fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.homeLayout, fragment)
                .commit()
        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.home -> loadFragment(HomeFragment())
                R.id.addemp -> loadFragment(AddEmp_Fragment())
                R.id.salary-> loadFragment(SalaryFragment())
                R.id.rate -> loadFragment(RatingsFragment())
                R.id.about -> loadFragment(AboutUsFragment())
            }
            return true
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                loadFragment(AddEmp_Fragment())
                return true
            }
            R.id.calc -> {
                loadFragment(SalaryFragment())
                return true
            }
            R.id.rate -> {
                loadFragment(RatingsFragment())
                return true
            }
            R.id.exit -> {
                finish()
                return true
            }
        }
        return true
    }
}