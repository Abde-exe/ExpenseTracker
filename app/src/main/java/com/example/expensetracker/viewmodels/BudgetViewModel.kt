package com.example.expensetracker.viewmodels

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.toBudgets

class BudgetViewModel : ViewModel() {

   /* var budgets = listOf<Budget>()
    var database = AppDatabaseSingleton.getInstance()
     var budgetDAO = database.budgetDao()
    fun getBudgets (){
        budgets = budgetDAO.getAllBudgets().toBudgets()
    }*/
}