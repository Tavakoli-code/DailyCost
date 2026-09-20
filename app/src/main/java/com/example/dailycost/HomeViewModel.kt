package com.example.dailycost

import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = transactionRepository.transactions
        .map { transactions ->
            val income = transactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }

            val expenses = transactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }

            val balance = income - expenses

            HomeUiState(
                totalBalance = "$balance AFN",
                monthlyIncome = "$income AFN",
                monthlyExpenses = "$expenses AFN"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState(
                totalBalance = "0 AFN",
                monthlyIncome = "0 AFN",
                monthlyExpenses = "0 AFN"
            )
        )

    fun addTestExpense() {
        viewModelScope.launch {
            transactionRepository.insert(
                TransactionEntity(
                    type = TransactionType.EXPENSE,
                    amount = 500,
                    category = "Food",
                    description = "Test lunch",
                    date = System.currentTimeMillis()
                )
            )
        }
    }
}