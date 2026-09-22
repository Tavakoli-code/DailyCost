package com.example.dailycost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailycost.ui.theme.DailyCostTheme

@Composable
fun TransactionsScreen(
    transactions: List<TransactionEntity>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = "Transactions")
        Button(
            onClick = onBack
        ) {
            Text(text = "Back")
        }
        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(
    transaction: TransactionEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = transaction.category,
            style = MaterialTheme.typography.titleMedium
        )

        if (!transaction.description.isNullOrBlank()) {
            Text(
                text = transaction.description
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = when (transaction.type) {
                    TransactionType.INCOME -> "Income"
                    TransactionType.EXPENSE -> "Expense"
                },
                modifier = Modifier.weight(1f)
            )

            val signedAmount = when (transaction.type) {
                TransactionType.INCOME -> "+${transaction.amount} AFN"
                TransactionType.EXPENSE -> "-${transaction.amount} AFN"
            }
            Text(
                text = signedAmount
            )
        }
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    DailyCostTheme {
        TransactionsScreen(
            transactions = listOf(
                TransactionEntity(
                    id = 1,
                    type = TransactionType.EXPENSE,
                    amount = 500,
                    category = "Food",
                    description = "Lunch",
                    date = System.currentTimeMillis()
                ),
                TransactionEntity(
                    id = 2,
                    type = TransactionType.INCOME,
                    amount = 50_000,
                    category = "Salary",
                    description = "Monthly salary",
                    date = System.currentTimeMillis()
                ),
                TransactionEntity(
                    id = 3,
                    type = TransactionType.EXPENSE,
                    amount = 200,
                    category = "Transport",
                    description = "Taxi",
                    date = System.currentTimeMillis()
                )
            ),
            onBack = {}
        )
    }
}