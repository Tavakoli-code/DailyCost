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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailycost.ui.theme.DailyCostTheme

data class HomeUiState(
    val totalBalance: String,
    val monthlyIncome: String,
    val monthlyExpenses: String,
) {}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyCostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(all = 8.dp)
    ) {
        Text(
            text = "Daily Cost", style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Total Balance")
        Text(
            text = "35,000 AFN", style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "This Month", style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            MonthlySummaryItem(
                label = "Income",
                amount = "50,000 AFN",
                modifier = Modifier.weight(1f)
            )
            MonthlySummaryItem(
                label = "Expenses",
                amount = "15,000 AFN",
                modifier = Modifier.weight(1f)
            )
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
        HomeScreen()
    }
}