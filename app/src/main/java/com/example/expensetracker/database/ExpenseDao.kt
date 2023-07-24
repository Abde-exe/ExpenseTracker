package com.example.expensetracker.database

import androidx.room.*

@Dao
interface ExpenseDao {
    @Insert
    fun insert(expense: ExpenseEntity)

    @Delete
    fun delete(expense: ExpenseEntity)

    @Update
    fun update(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getById(id: Int): ExpenseEntity
}