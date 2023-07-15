package com.example.expensetracker.database

import androidx.room.*

@Dao
interface BudgetDao {
    @Insert
    fun insert(budget: BudgetEntity)

    @Update
    fun update(budget: BudgetEntity)

    @Delete
    fun delete(budget: BudgetEntity)

    @Query("SELECT * FROM budgets")
    fun getAllBudgets(): List<BudgetEntity>

    @Query("SELECT * FROM budgets WHERE id = :id")
    fun getById(id: Int): BudgetEntity
}
