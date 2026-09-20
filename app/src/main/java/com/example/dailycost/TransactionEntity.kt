package com.example.dailycost

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val type: TransactionType,
    val amount: Long,
    val category: String,
    val description: String?,
    val date: Long
)