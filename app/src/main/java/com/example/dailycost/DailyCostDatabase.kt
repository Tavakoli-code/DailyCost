package com.example.dailycost

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DailyCostDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {

        @Volatile
        private var INSTANCE: DailyCostDatabase? = null

        fun getDatabase(context: Context): DailyCostDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DailyCostDatabase::class.java,
                    "daily_cost_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}