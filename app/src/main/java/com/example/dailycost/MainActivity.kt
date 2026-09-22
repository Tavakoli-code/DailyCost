package com.example.dailycost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailycost.ui.theme.DailyCostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val application = LocalContext.current.applicationContext as DailyCostApplication

            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    transactionRepository = application.transactionRepository
                )
            )
            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
            DailyCostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAddTransaction: () -> Unit,
    onViewTransactions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(all = 8.dp)
    ) {
        Text(
            text = "Daily Cost", style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Total Balance")
        Text(
            text = state.totalBalance, style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Summary", style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            MonthlySummaryItem(
                label = "Income",
                amount = state.monthlyIncome,
                modifier = Modifier.weight(1f)
            )
            MonthlySummaryItem(
                label = "Expenses",
                amount = state.monthlyExpenses,
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = onAddTransaction
        ) {
            Text(text = "Add Transaction")
        }
        Button(
            onClick = onViewTransactions
        ) {
            Text(text = "Transactions")
        }
    }
}

@Composable
fun MonthlySummaryItem(
    label: String,
    amount: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = label)
        Text(text = amount)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DailyCostTheme {
        HomeScreen(
            state = HomeUiState(
                totalBalance = "35,000 AFN",
                monthlyIncome = "50,000 AFN",
                monthlyExpenses = "15,000 AFN",
            ),
            onAddTransaction = {},
            onViewTransactions = {}
        )
    }
}