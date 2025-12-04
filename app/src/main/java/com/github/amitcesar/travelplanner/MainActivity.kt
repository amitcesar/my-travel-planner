package com.github.amitcesar.travelplanner

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.github.amitcesar.travelplanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        as? NavHostFragment
        navHostFragment?.navController
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSystemBars()
        setupButtonListeners()
        setupNavigationListener()
    }


    private fun setupSystemBars() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContainer) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    private fun setupButtonListeners() {
        binding.btnStart.setOnClickListener {
            when(navController?.currentDestination?.id) {
                R.id.startFragment -> {
                    navController?.navigate(R.id.distanceFragment)
                }
                R.id.distanceFragment -> {
                    navController?.navigate(R.id.averageConsumptionFragment)
                }
                R.id.averageConsumptionFragment -> {
                    navController?.navigate(R.id.priceFragment)
                }
                R.id.priceFragment -> {
                    navController?.popBackStack(R.id.startFragment, false)
                }
            }
        }
    }

    private fun updateUI(destinationId: Int) {

        when (destinationId) {
            R.id.startFragment -> {
                binding.tvSubtitle.visibility = View.VISIBLE
                binding.btnStart.visibility = View.VISIBLE

            }
            R.id.distanceFragment -> {

                binding.tvSubtitle.visibility = View.GONE
                binding.btnStart.visibility = View.GONE

            }
            R.id.averageConsumptionFragment -> {
                binding.tvSubtitle.visibility = View.GONE
                binding.btnStart.visibility = View.GONE

            }
            R.id.priceFragment -> {
                binding.tvSubtitle.visibility = View.GONE
                binding.btnStart.visibility = View.GONE

            }
        }
    }

    private fun setupNavigationListener(){
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            updateUI(destination.id)
        }
    }
}



