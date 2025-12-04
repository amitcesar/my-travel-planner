package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.amitcesar.travelplanner.databinding.FragmentAverageConsumptionBinding
import com.github.amitcesar.travelplanner.databinding.FragmentDistanceBinding
import kotlin.getValue


class AverageConsumptionFragment : Fragment() {

    private val viewModel: PlannerViewModel by activityViewModels()
    private var _binding: FragmentAverageConsumptionBinding? = null
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
        _binding = FragmentAverageConsumptionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun setupButtonListeners() {

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnNext.setOnClickListener {
            findNavController()
                .navigate(R.id.action_averageConsumptionFragment_to_priceFragment)

            val consumption = binding.editConsumptionValue.text.toString()

            if (consumption.isEmpty()){
                binding.editConsumptionValue.error = "Digite um valor para o campo"
                return@setOnClickListener
            }
        }
    }

    private fun inputOnChanged(){
        binding.editConsumptionValue.addTextChangedListener{ inputText ->
            viewModel.setAverageConsumptionInputValue(
                averageConsumptionValue = inputText.toString().toIntOrNull() ?: 0
            )
        }
    }

}