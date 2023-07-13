package com.example.expensetracker.models

import android.net.Uri
import com.example.expensetracker.utils.JsonNavType
import com.google.gson.Gson

data class Budget(val amount: Float, val currency: String, val spent: Float, val category: String) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class BudgetArgType : JsonNavType<Budget>(){
    override fun fromJsonParse(value: String): Budget = Gson().fromJson(value, Budget::class.java)

    override fun Budget.getJsonParse(): String = Gson().toJson(this)
}