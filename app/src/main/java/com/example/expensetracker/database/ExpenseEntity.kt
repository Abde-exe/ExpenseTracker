package com.example.expensetracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "amount")
    val amount: Float,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "changedAmount")
    val changedAmount: Float,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "paymentType")
    var paymentType: String,
    @ColumnInfo(name = "date")
    var date: Long
   //, @ColumnInfo(name = "images")
    //var images: Array<URL>?
)