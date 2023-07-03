package com.example.expensetracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.views.BudgetScreen
import com.example.expensetracker.views.HomeScreen
import com.example.expensetracker.views.NewScreen
import com.example.expensetracker.views.ReportScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Expenses.route) {
            ExpensesScreen()
        }
        composable(route = BottomBarScreen.NewExpense.route) {
            NewScreen()
        }
        composable(route = BottomBarScreen.Report.route) {
            ReportScreen()
        }
        composable(route = BottomBarScreen.Budget.route) {
            BudgetScreen()
        }
    }
}

