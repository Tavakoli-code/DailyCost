package com.example.dailycost

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: TransactionEntity): Long

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query(
        """
        SELECT * FROM transactions
        WHERE date >= :startDate AND date < :endDate
        ORDER BY date DESC
        """
    )
    fun getTransactionsBetween(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>>
}