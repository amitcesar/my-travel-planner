package com.github.amitcesar.travelplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.amitcesar.travelplanner.databinding.FragmentCostResultsBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale


class CostResultsFragment : Fragment() {

    private val viewModel: PlannerViewModel by activityViewModels()
    private var _binding: FragmentCostResultsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flowCostResultsHelper.referencedIds = intArrayOf(
            R.id.tv_distance_result,
            R.id.tv_consumption_result,
            R.id.tv_price_result,
            R.id.tv_total_cost_result
        )

        viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect {  uiState ->
                        updateUI(uiState)
                    }
                }

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCostResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateUI(uiState: UiState) {
        val distance = uiState.distanceValue
        val consumption = uiState.averageConsumptionValue
        val price = uiState.priceValue
        val totalCost = uiState.totalCost


        val distanceText = "Distância: ${distance.toInt()} km"
        val consumptionText = "Consumo: ${consumption.toInt()} km/L"
        val priceText = "Preço do Combustível: ${formatCurrency(price)}"
        val totalCostText = "Custo Total Estimado: ${formatCurrency(totalCost)}"


        binding.tvCostResultText.text = "Detalhes do Custo:"

        binding.tvDistanceResult.text = distanceText
        binding.tvConsumptionResult.text = consumptionText
        binding.tvPriceResult.text = priceText
        binding.tvCostResultText.text = totalCostText
    }

    private fun formatCurrency(value: Double): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return currencyFormat.format(value)
    }


}