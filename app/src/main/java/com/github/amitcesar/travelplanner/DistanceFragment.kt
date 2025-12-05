package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.amitcesar.travelplanner.databinding.FragmentDistanceBinding


class DistanceFragment : Fragment() {

    private val viewModel: PlannerViewModel by activityViewModels()
    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonListeners()
        inputOnChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    private fun setupButtonListeners() {

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
            //findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {

            val distanceText = binding.editDistanceValue.text.toString()

            if (distanceText.isEmpty() || distanceText.toDoubleOrNull() == null || distanceText.toDoubleOrNull() == 0.0){
                binding.editDistanceValue.error = "Digite uma distância"
                return@setOnClickListener
            }

            findNavController()
                .navigate(R.id.action_distanceFragment_to_averageConsumptionFragment)
        }

    }

    private fun inputOnChanged() {
        binding.editDistanceValue.addTextChangedListener { inputText ->
            viewModel.setDistanceInputValue(
                distanceValue =  inputText.toString().toDoubleOrNull() ?: 0.0
            )
        }
    }


}