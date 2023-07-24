package com.example.expensetracker

import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.Expense
import java.util.*

object Constants {

    private val budgetslist: MutableList<Budget> = mutableListOf()
    private val expenseslist: MutableList<Expense> = mutableListOf()

    enum class Categories(val categoryName: String, val color: Int?) {
        SHOPPING("Shopping", 0xFF0000),
        TRANSP("Transportation", 0x00FF00),
        NS("specified", 0x0000FF)
    }

    enum class PaymentType(val typeName: String) {
        CARD("Card"),
        CASH("Cash"),
        TRANSFER("Transfer"),
    }

    enum class Currencies(val currencyName: String, val symbol: Char) {
        EUR("EUR", '€'),
        TRY("TRY", '₺')
    }

    fun getBudgets(): MutableList<Budget> {

        val b1 = Budget(0, 0f, Currencies.EUR.currencyName, spent = 0f, Categories.NS.name)
        val b2 = Budget(1, 200f, Currencies.TRY.currencyName, spent = 18f, Categories.TRANSP.name)
        val b3 = Budget(2, 300f, Currencies.EUR.currencyName, spent = 50f, Categories.SHOPPING.name)

        budgetslist.add(b1)
        budgetslist.add(b2)
        budgetslist.add(b3)
        return budgetslist
    }

    fun addBudget(newBudget: Budget) {
        budgetslist.add(newBudget)
    }

    fun getExpenses(): MutableList<Expense> {

        val e1 = Expense(
            id = 0,
            title = "title1",
            description = "",
            amount = 1000f,
            currency = "EUR",
            changedAmount = 1000f * 28f,
            category = Categories.TRANSP.categoryName,
            paymentType =  PaymentType.CARD.typeName,
            date = Date().time
            //, images = null
        )
        val e2 = Expense(
            id = 1,
            title = "title2",
            description = "",
            amount = 10000f,
            currency = "TRY",
            changedAmount = 10000f * 0.035f,
            category = Categories.SHOPPING.categoryName,
            paymentType =  PaymentType.CASH.typeName,
            date = Date().time
            //, images = null
        )



        expenseslist.add(e1)
        expenseslist.add(e2)
        //expenseslist.add(e3)
        return expenseslist
    }

    fun addExpense(newExpense: Expense) {
        expenseslist.add(newExpense)
    }
}