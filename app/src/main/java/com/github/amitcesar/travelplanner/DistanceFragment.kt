package com.github.amitcesar.travelplanner

import android.os.Bundle
import android.util.Log
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

    // TO DO - FAZER UMA FUN SEPARADA PARA VALIDACAO DE CAMPOOS.
    private fun setupButtonListeners() {

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
            //findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {

            findNavController()
                .navigate(R.id.action_distanceFragment_to_averageConsumptionFragment)

            val distance = binding.editDistanceValue.text.toString()

            if (distance.isEmpty()){
                binding.editDistanceValue.error = "Digite uma distância"
                return@setOnClickListener
            }
        }

    }

    private fun inputOnChanged() {
        binding.editDistanceValue.addTextChangedListener { inputText ->
            viewModel.setDistanceInputValue(
                distanceValue = inputText.toString().toIntOrNull() ?: 0
            )
        }
    }


}