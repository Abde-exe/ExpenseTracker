package com.example.expensetracker.models

import android.net.Uri
import com.example.expensetracker.database.BudgetEntity
import com.example.expensetracker.utils.JsonNavType
import com.google.gson.Gson

data class Budget(val id: Int, val amount: Float, val currency: String, val spent: Float, val category: String) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class BudgetArgType : JsonNavType<Budget>(){
    override fun fromJsonParse(value: String): Budget = Gson().fromJson(value, Budget::class.java)

    override fun Budget.getJsonParse(): String = Gson().toJson(this)
}

fun Budget.toBudgetEntity(): BudgetEntity {
    return BudgetEntity(
        id = id,
        amount = amount,
        currency = currency,
        spent = spent,
        category = category
    )
}
fun List<BudgetEntity>.toBudgets(): List<Budget> {
    return map { budgetEntity ->
        Budget(
            id = budgetEntity.id,
            amount = budgetEntity.amount,
            currency = budgetEntity.currency,
            spent = budgetEntity.spent,
            category = budgetEntity.category
        )
    }
}