package com.example.expensetracker.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.utils.DateConverter

@Database(entities = [BudgetEntity::class, ExpenseEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao

    abstract fun expenseDao(): ExpenseDao
}

object AppDatabaseSingleton {
    private var instance: AppDatabase? = null

    private val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajoutez ici les instructions SQL pour migrer la base de données de la version 1 à la version 2
        }
    }

    private val migration_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajoutez ici les instructions SQL pour migrer la base de données de la version 1 à la version 2
        }
    }
    private val migration_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajoutez ici les instructions SQL pour migrer la base de données de la version 1 à la version 2
        }
    }

    private val migration_2_4 = object : Migration(2, 4) {
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
            ).build()
        }
        return instance!!
    }
}