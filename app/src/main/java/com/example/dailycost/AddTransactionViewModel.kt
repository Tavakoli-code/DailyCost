package com.example.dailycost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    fun saveTransaction(
        type: TransactionType,
        amount: Long,
        description: String?,
        onSaved: () -> Unit
    ) {
        viewModelScope.launch {
            transactionRepository.insert(
                TransactionEntity(
                    type = type,
                    amount = amount,
                    category = "Other",
                    description = description,
                    date = System.currentTimeMillis()
                )
            )

            onSaved()
        }
    }
}