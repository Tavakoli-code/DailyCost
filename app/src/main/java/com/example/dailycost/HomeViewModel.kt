package com.example.dailycost

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var uiState by mutableStateOf(
        HomeUiState(
            totalBalance = "35,000 AFN",
            monthlyIncome = "50,000 AFN",
            monthlyExpenses = "15,000 AFN"
        )
    )
        private set

    fun changeBalance() {
        uiState = uiState.copy(
            totalBalance = "40,000 AFN"
        )
    }
}