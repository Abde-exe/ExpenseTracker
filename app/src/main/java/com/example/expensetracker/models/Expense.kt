package com.example.expensetracker.models

import com.example.expensetracker.Constants
import java.net.URL
import java.util.Date


data class Expense(
    val title: String,
    val description: String?,
    val amount: Float,
    val currency: String,
    val changedAmount: Float,
    val category: String,
    val paymentType : String,
    val date: Date,
    val images: Array<URL>?
)