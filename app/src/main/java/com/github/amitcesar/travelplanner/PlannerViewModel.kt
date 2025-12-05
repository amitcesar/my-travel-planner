package com.github.amitcesar.travelplanner

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val distanceValue: Double = 0.0,
    val averageConsumptionValue: Double = 0.0,
    val priceValue: Double = 0.0,
    val totalCost: Double = 0.0,
    val totalCostsList: List<Double> = emptyList()
)

class PlannerViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private fun setTotalCost(totalCost: Double) {
        _uiState.value = _uiState.value.copy(totalCost = totalCost)

    }

    private fun calculateAndSetTotalCost(){
        val state = _uiState.value
        val distance = state.distanceValue
        val consumption = state.averageConsumptionValue
        val price = state.priceValue

        val total = if (consumption > 0.0) {
            (distance / consumption) * price
        } else {
            0.0
        }

        // Chama seu setter para atualizar o StateFlow
        setTotalCost(total)
        // 1. Formatação da String do Log
        val logMessage = """
        Distância: ${distance.toInt()} km
        Consumo: ${consumption} km/L
        Preço do Combustível: R$ ${"%.2f".format(price)}
        Custo Total Estimado: R$ ${"%.2f".format(total)}
    """.trimIndent()


        Log.d("PlannerViewModel", ":$logMessage:")
    }




    fun setDistanceInputValue(distanceValue: Double) {
//        Log.d("FUNCAO BATEU", distanceValue.toString())
        _uiState.value = _uiState.value.copy(distanceValue = distanceValue)
        calculateAndSetTotalCost()
    }

    fun setAverageConsumptionInputValue(averageConsumptionValue: Double) {
        _uiState.value = _uiState.value.copy(averageConsumptionValue = averageConsumptionValue)
        calculateAndSetTotalCost()
    }

    fun setPriceInputValue(priceValue: Double) {
        _uiState.value = _uiState.value.copy(priceValue = priceValue)
        calculateAndSetTotalCost()
    }





}