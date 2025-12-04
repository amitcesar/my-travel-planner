package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.amitcesar.travelplanner.databinding.FragmentAverageConsumptionBinding
import com.github.amitcesar.travelplanner.databinding.FragmentDistanceBinding


class AverageConsumptionFragment : Fragment() {

    private var _binding: FragmentAverageConsumptionBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAverageConsumptionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}