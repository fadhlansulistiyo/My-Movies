package com.dicoding.mymovies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var activityHomeBinding: ActivityHomeBinding? = null
    private val binding get() = activityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val bottomNavView = binding?.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        if (bottomNavView != null) {
            NavigationUI.setupWithNavController(
                bottomNavView,
                navHostFragment.navController
            )
        }
    }

    fun setActionBar(title: String) {
        supportActionBar?.title = title
    }
    override fun onDestroy() {
        super.onDestroy()
        activityHomeBinding = null
    }
}