package com.example.dailycost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddTransactionViewModelFactory(
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTransactionViewModel(transactionRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}