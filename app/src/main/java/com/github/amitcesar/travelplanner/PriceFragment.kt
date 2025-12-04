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

            findNavController().popBackStack(R.id.startFragment, false)

            val price = binding.editPriceValue.text.toString()
            if (price.isEmpty()){

                binding.editPriceValue.error = "Digite uma distância"
                return@setOnClickListener
            }
        }

    }

    private fun inputOnChanged() {
        binding.editPriceValue.addTextChangedListener{ inputText ->
            viewModel.setDistanceInputValue(
                distanceValue = inputText.toString().toIntOrNull() ?: 0
            )

        }
    }
}