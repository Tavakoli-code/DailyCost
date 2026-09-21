package com.example.dailycost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddTransactionScreen(
    onBack: () -> Unit,
    onSave: (TransactionType, Long, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var transactionType by remember {
        mutableStateOf(TransactionType.EXPENSE)
    }

    val amountValue = amount.toLongOrNull()

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Add Transaction",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    transactionType = TransactionType.EXPENSE
                }
            ) {
                Text(text = "Expense")
            }

            Button(
                onClick = {
                    transactionType = TransactionType.INCOME
                }
            ) {
                Text(text = "Income")
            }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = {
                Text("Amount")
            }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = {
                Text("Description")
            }
        )

        Row {
            Button(
                onClick = {
                    amountValue?.let {
                        onSave(
                            transactionType,
                            it,
                            description.ifBlank { null }
                        )
                    }
                },
                enabled = amountValue != null && amountValue > 0
            ) {
                Text("Save")
            }

            Button(
                onClick = onBack
            ) {
                Text(text = "Back")
            }
        }
    }
}