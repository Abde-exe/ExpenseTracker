package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.nav.RootNavigationGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootNavigationGraph(navController = rememberNavController() )
        }
    }
}



