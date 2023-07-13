package com.example.expensetracker.models

import android.net.Uri
import com.example.expensetracker.utils.JsonNavType
import com.google.gson.Gson
import java.net.URL
import java.util.Date


data class Expense(
    val title: String,
    val description: String?,
    val amount: Float,
    val currency: String,
    val changedAmount: Float,
    val category: String,
    val paymentType: String,
    val date: Date,
    val images: Array<URL>?
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class ExpenseArgtype : JsonNavType<Expense>() {
    override fun fromJsonParse(value: String): Expense = Gson().fromJson(value, Expense::class.java)

    override fun Expense.getJsonParse(): String = Gson().toJson(this)
}
