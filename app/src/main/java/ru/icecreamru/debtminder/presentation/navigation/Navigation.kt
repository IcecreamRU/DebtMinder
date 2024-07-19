package ru.icecreamru.debtminder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.icecreamru.debtminder.presentation.ui.debtlist.DebtListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "debtList") {
        composable("debtList") {
            DebtListScreen()
        }
    }
}