package com.example.expensetracker.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.*
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.BudgetArgType
import com.example.expensetracker.views.BudgetCreate
import com.example.expensetracker.views.BudgetDetails
import com.google.gson.Gson


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
        composable(route = BudgetScreen.Details.route, arguments = listOf(navArgument("budget") {
            type = BudgetArgType()
        })) {
            val budget : Budget? = it.arguments?.getString("budget")?.let { Gson().fromJson(it, Budget::class.java) }
            //Log.d("Args",it.arguments?.getString("budget").toString())
            BudgetDetails(navController, budget!!)
        }
        composable(route = BudgetScreen.Create.route) {
            BudgetCreate(navController)
        }
    }
}

sealed class ExpenseScreen(val route: String) {
    object Details : ExpenseScreen(route = "DETAILS")
}

sealed class BudgetScreen(val route: String) {
    object Details : BudgetScreen(route = "DETAILS/{budget}")
    object Create : BudgetScreen(route = "CREATE")
}

