package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.amitcesar.travelplanner.databinding.FragmentPriceBinding
import kotlin.getValue


class PriceFragment : Fragment() {


    private val viewModel: PlannerViewModel by activityViewModels()
    private var _binding: FragmentPriceBinding? = null
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
        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtonListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()

        }

        binding.btnNext.setOnClickListener {

            val priceText = binding.editPriceValue.text.toString()
            if (priceText.isEmpty() || priceText.toDoubleOrNull() == null || priceText.toDoubleOrNull() == 0.0){

                binding.editPriceValue.error = "Digite o preco corretamente"
                return@setOnClickListener
            }

            findNavController().navigate(R.id.action_priceFragment_to_costResultsFragment)


        }

    }

    private fun inputOnChanged() {
        binding.editPriceValue.addTextChangedListener{ inputText ->
            viewModel.setPriceInputValue(
                priceValue = inputText.toString().toDoubleOrNull() ?: 0.0
            )

        }
    }
}