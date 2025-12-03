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
            when(binding.btnStart.text.toString()) {
                getString(R.string.btn_start_text) -> {
                    navController?.navigate(R.id.distanceFragment)
                    binding.btnStart.text = getString(R.string.btn_back_button)
                }
                getString(R.string.btn_back_button) -> {
                    binding.btnStart.text = getString(R.string.btn_back_button)
                }
                else -> {
                    binding.btnStart.text = getString(R.string.btn_start_text)
            }

        }


        }

    }











}



