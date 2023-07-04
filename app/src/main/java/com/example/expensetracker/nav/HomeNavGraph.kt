package com.example.expensetracker.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.expensetracker.*


@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route){
            HomeContent(onClick = {navController.navigate(Graph.DETAILS)})
        }
        composable(route = BottomBarScreen.Expenses.route) {
           ExpensesScreen(onClick = {navController.navigate(Graph.DETAILS)})
        }
        composable(route = BottomBarScreen.NewExpense.route) {

        }
        composable(route = BottomBarScreen.Report.route) {
            ReportScreen()
        }
        composable(route = BottomBarScreen.Budget.route) {
            BudgetScreen()
        }
        detailsNavGraph(navController  = navController)

    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ExpenseDetails(navController =  navController)
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
}

