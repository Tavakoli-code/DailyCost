package com.example.dailycost

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            totalBalance = "35,000 AFN",
            monthlyIncome = "50,000 AFN",
            monthlyExpenses = "15,000 AFN"
        )
    )

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}