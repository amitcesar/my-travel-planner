package com.github.amitcesar.travelplanner

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val distanceValue: Int = 0,
    val averageConsumptionValue: Int = 0,
    val priceValue: Int = 0,
    val totalCost: Int = 0,
    val totalCostsList: List<Int> = emptyList()
)

class PlannerViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun setDistanceInputValue(distanceValue: Int) {
        Log.d("FUNCAO BATEU", distanceValue.toString())
        _uiState.value = _uiState.value.copy(distanceValue = distanceValue)
    }

    fun setAverageConsumptionInputValue(averageConsumptionValue: Int) {
        _uiState.value = _uiState.value.copy(averageConsumptionValue = averageConsumptionValue)
    }

    fun setPriceInputValue(priceValue: Int) {
        _uiState.value = _uiState.value.copy(priceValue = priceValue)
    }

    fun calculateTotalCost(distanceValue: Int, averageConsumptionValue: Int, priceValue: Int){
        val total =  ( distanceValue / averageConsumptionValue ) * priceValue
    }


}