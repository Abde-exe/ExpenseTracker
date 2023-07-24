package com.example.expensetracker.models

import android.net.Uri
import com.example.expensetracker.database.ExpenseEntity
import com.example.expensetracker.utils.JsonNavType
import com.google.gson.Gson
import java.net.URL
import java.sql.Date


data class Expense(
    val id: Int,
    val title: String,
    val description: String?,
    val amount: Float,
    val currency: String,
    val changedAmount: Float,
    val category: String,
    val paymentType: String,
    val date: Long
    //, val images: Array<URL>?
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class ExpenseArgtype : JsonNavType<Expense>() {
    override fun fromJsonParse(value: String): Expense = Gson().fromJson(value, Expense::class.java)

    override fun Expense.getJsonParse(): String = Gson().toJson(this)
}

fun Expense.toExpenseEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = id,
        title = title,
        description = description,
        amount = amount,
        currency = currency,
        changedAmount = changedAmount,
        category = category,
        paymentType = paymentType,
        date = date
        //, images = images
    )
}

fun List<ExpenseEntity>.toExpenses(): List<Expense> {
    return map { expenseEntity ->
        Expense(
            id = expenseEntity.id,
            title = expenseEntity.title,
            description = expenseEntity.description,
            amount = expenseEntity.amount,
            currency = expenseEntity.currency,
            changedAmount = expenseEntity.changedAmount,
            category = expenseEntity.category,
            paymentType = expenseEntity.paymentType,
            date = expenseEntity.date
            //, images = expenseEntity.images
        )
    }
}
