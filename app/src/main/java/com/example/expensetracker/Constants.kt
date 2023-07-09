package com.example.expensetracker

import com.example.expensetracker.models.Budget

object Constants {

    private val budgetslist: MutableList<Budget> = mutableListOf()

    enum class Categories(val categoryName: String, val color: Int?) {
        SHOPPING("Shopping", 0xFF0000),
        TRANSP("Transportation", 0x00FF00),
        NS("specified", 0x0000FF)
    }

    enum class Currencies(val currencyName: String) {
        EUR("EUR"),
        TRY("TRY")
    }

    fun getBudgets(): MutableList<Budget> {


        val b1 = Budget(100f, Currencies.EUR.currencyName, spent = 0f, Categories.SHOPPING.name)
        val b2 = Budget(200f, Currencies.TRY.currencyName, spent = 18f, Categories.TRANSP.name)
        val b3 = Budget(300f, Currencies.EUR.currencyName, spent = 50f, Categories.NS.name)

        budgetslist.add(b1)
        budgetslist.add(b2)
        budgetslist.add(b3)
        return budgetslist
    }

    fun addBudget(newBudget: Budget) {
        budgetslist.add(newBudget)
    }
}