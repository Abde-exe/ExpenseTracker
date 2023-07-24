package com.example.expensetracker.nav

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.*
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.BudgetArgType
import com.example.expensetracker.models.Expense
import com.example.expensetracker.models.ExpenseArgtype
import com.example.expensetracker.views.Budget.BudgetCreate
import com.example.expensetracker.views.BudgetDetails
import com.example.expensetracker.views.BudgetEdit
import com.google.gson.Gson

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            HomeContent(navController)
        }
        composable(route = BottomBarScreen.Expenses.route) {
            ExpensesScreen(navController)
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
        detailsNavGraph(navController)
        budgetNavGraph(navController)

    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EXPENSE,
        startDestination = ExpenseScreen.Details.route
    ) {
        composable(route = ExpenseScreen.Details.route, listOf(navArgument("expense") {
            type = ExpenseArgtype()
        })) {
            val expense: Expense? =
                it.arguments?.getString("expense")?.let { Gson().fromJson(it, Expense::class.java) }
            ExpenseDetails(navController, expense!!)
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
            val budget: Budget? =
                it.arguments?.getString("budget")?.let { Gson().fromJson(it, Budget::class.java) }
            BudgetDetails(navController, budget!!)
        }
        composable(route = BudgetScreen.Create.route){
            BudgetCreate(navController)
        }
        composable(route = BudgetScreen.Edit.route, arguments = listOf(navArgument("budget") {
            type = BudgetArgType()
            defaultValue = Constants.getBudgets()[0]
        })) {
            val budget: Budget? =
                it.arguments?.getString("budget")?.let { Gson().fromJson(it, Budget::class.java) }
            //Log.d("test", budget?.amount.toString())
            BudgetEdit(navController, budget!!)
        }
    }
}

sealed class ExpenseScreen(val route: String) {
    object Details : ExpenseScreen(route = "EXPENSEDETAILS/{expense}")
}

sealed class BudgetScreen(val route: String) {
    object Details : BudgetScreen(route = "DETAILS/{budget}")
    object Create : BudgetScreen(route = "CREATE")
    object Edit : BudgetScreen(route = "EDIT?budget={budget}"){
        fun passBudget(budget: Budget):String{
            return "EDIT?budget=$budget"
        }
    }
}

