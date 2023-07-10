package com.example.expensetracker.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.expensetracker.*
import com.example.expensetracker.views.BudgetCreate
import com.example.expensetracker.views.BudgetDetails


@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            HomeContent(onClick = { navController.navigate(Graph.EXPENSE) })
        }
        composable(route = BottomBarScreen.Expenses.route) {
            ExpensesScreen(onClick = { navController.navigate(Graph.EXPENSE) })
        }
        composable(route = BottomBarScreen.NewExpense.route) {
            NewScreen(navController)
        }
        composable(route = BottomBarScreen.Report.route) {
            ReportScreen()
        }
        composable(route = BottomBarScreen.Budget.route) {
            BudgetScreen(navController)
        }
        detailsNavGraph(navController = navController)
        budgetNavGraph(navController)

    }
}



fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EXPENSE,
        startDestination = ExpenseScreen.Details.route
    ) {
        composable(route = ExpenseScreen.Details.route) {
            ExpenseDetails(navController = navController)
        }
    }
}
fun NavGraphBuilder.budgetNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.BUDGET,
        startDestination = BudgetScreen.Details.route
    ) {
        composable(route = BudgetScreen.Details.route) {
            BudgetDetails(navController = navController)
        }
        composable(route = BudgetScreen.Create.route){
            BudgetCreate(navController)
        }
    }
}

sealed class ExpenseScreen(val route: String) {
    object Details : ExpenseScreen(route = "DETAILS")
}
sealed class BudgetScreen(val route: String) {
    object Details : BudgetScreen(route = "DETAILS")
    object Create : BudgetScreen(route = "CREATE")
}

