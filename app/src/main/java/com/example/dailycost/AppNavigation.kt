package com.example.dailycost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable

object Routes {
    const val HOME = "home"
    const val ADD_TRANSACTION = "add_transaction"
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val application =
        LocalContext.current.applicationContext as DailyCostApplication

    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            transactionRepository = application.transactionRepository
        )
    )

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                state = uiState,
                onAddTransaction = {
                    navController.navigate(Routes.ADD_TRANSACTION)
                }
            )
        }

        composable(Routes.ADD_TRANSACTION) {
            val addTransactionViewModel: AddTransactionViewModel = viewModel(
                factory = AddTransactionViewModelFactory(
                    transactionRepository = application.transactionRepository
                )
            )

            AddTransactionScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSave = { type, amount, description ->
                addTransactionViewModel.saveTransaction(
                    type = type,
                    amount = amount,
                    description = description,
                    onSaved = {
                        navController.popBackStack()
                    }
                    )
                }
            )
        }
    }
}