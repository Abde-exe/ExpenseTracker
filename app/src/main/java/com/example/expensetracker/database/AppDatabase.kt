package com.example.expensetracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BudgetEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
}

object AppDatabaseSingleton {
    private var instance: AppDatabase? = null

    private val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajoutez ici les instructions SQL pour migrer la base de données de la version 1 à la version 2
        }
    }

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "my-database-name"
            ).addMigrations(migration_1_2)
                .build()
        }
        return instance!!
    }
}