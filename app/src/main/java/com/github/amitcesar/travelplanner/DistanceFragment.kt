package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.amitcesar.travelplanner.databinding.FragmentDistanceBinding


class DistanceFragment : Fragment() {

    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupEditListener() {
        binding.editDistanceValue.setOnFocusChangeListener {_, hasFocus ->
            if(!hasFocus) {
                val value = binding.editDistanceValue.text.toString()
                if (value.isEmpty() || value.toInt() < 1) {
                    binding.editDistanceValue.setText("1")
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}