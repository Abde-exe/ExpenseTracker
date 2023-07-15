package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.database.AppDatabase
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.nav.RootNavigationGraph


class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database= AppDatabaseSingleton.getInstance(applicationContext)

        setContent {
            RootNavigationGraph(navController = rememberNavController())
        }
    }
}



