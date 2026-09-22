package com.example.dailycost

import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import kotlinx.coroutines.flow.combine
class HomeViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val monthRange: Pair<Long, Long> = getCurrentMonthRange()

    val uiState: StateFlow<HomeUiState> =
        combine(
            transactionRepository.transactions,
            transactionRepository.getTransactionsBetween(monthRange.first, monthRange.second)
        ) { allTransactions, monthlyTransactions ->
            val income = allTransactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }
            val expenses = allTransactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }
            val balance = income - expenses

            val monthlyIncome = monthlyTransactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }
            val monthlyExpenses = monthlyTransactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }

            HomeUiState(
                totalBalance = "$balance AFN",
                monthlyIncome = "$monthlyIncome AFN",
                monthlyExpenses = "$monthlyExpenses AFN"
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

    private fun getCurrentMonthRange(): Pair<Long, Long> {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val startOfMonth = calendar.timeInMillis

        calendar.add(Calendar.MONTH, 1)

        val startOfNextMonth = calendar.timeInMillis

        return startOfMonth to startOfNextMonth
    }
}