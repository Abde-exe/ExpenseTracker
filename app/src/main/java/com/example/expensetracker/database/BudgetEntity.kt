package com.example.expensetracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgets")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "amount")
    var amount: Float,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "spent")
    var spent: Float,
    @ColumnInfo(name = "category")
    var category: String,
    )