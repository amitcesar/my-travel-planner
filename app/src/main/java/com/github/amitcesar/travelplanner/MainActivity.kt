package com.github.amitcesar.travelplanner

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.amitcesar.travelplanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFragmentVisible = false



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
            if (!isFragmentVisible) {
                showFragment()
            } else {
                hideFragment()
            }

            isFragmentVisible = !isFragmentVisible

        }
    }

    private fun showFragment(){
        binding.fragmentContainer.visibility = View.VISIBLE
        binding.btnStart.text = "Voltar"

        val firstFragment = DistanceFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, firstFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun hideFragment(){
        binding.btnStart.text = "Começar"
        binding.fragmentContainer.visibility = View.GONE
        supportFragmentManager.popBackStack()


    }





}



