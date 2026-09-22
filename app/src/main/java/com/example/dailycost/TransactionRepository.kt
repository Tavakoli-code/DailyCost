package com.example.dailycost

import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val transactionDao: TransactionDao
) {

    val transactions: Flow<List<TransactionEntity>> =
        transactionDao.getAllTransactions()

    suspend fun insert(transaction: TransactionEntity): Long {
        return transactionDao.insert(transaction)
    }

    fun getTransactionsBetween(
        startDate: Long,
        endDate: Long,
    ): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsBetween(
            startDate = startDate,
            endDate = endDate
        )
    }
}