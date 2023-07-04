package com.example.expensetracker.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Expenses : BottomBarScreen(
        route = "expenses",
        title = "Expenses",
        icon = Icons.Default.List
    )
    object NewExpense : BottomBarScreen(
        route = "newExpense",
        title = "New",
        icon = Icons.Default.AddCircle
    )
    object Report : BottomBarScreen(
        route = "report",
        title = "Report",
        icon = Icons.Default.AccountBox
    )
    object Budget : BottomBarScreen(
        route = "budget",
        title = "Budget",
        icon = Icons.Default.Build
    )

}