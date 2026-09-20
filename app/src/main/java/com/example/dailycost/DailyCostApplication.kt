package com.example.dailycost

import android.app.Application

class DailyCostApplication : Application() {

    val database by lazy {
        DailyCostDatabase.getDatabase(this)
    }

    val transactionRepository by lazy {
        TransactionRepository(
            transactionDao = database.transactionDao()
        )
    }
}