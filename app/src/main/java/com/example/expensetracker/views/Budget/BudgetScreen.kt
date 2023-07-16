package com.example.expensetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.toBudgets
import com.example.expensetracker.nav.BudgetScreen
import com.example.expensetracker.views.ValidateBtn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


private var budgets: List<Budget>? = null

@Composable
fun BudgetScreen(navController: NavHostController) {
    val bgColor = Color(0xFF7F3DFF)
    val database = AppDatabaseSingleton.getInstance(LocalContext.current)
    val budgetDAO = database.budgetDao()
    LaunchedEffect(Unit) {
        budgets = withContext(Dispatchers.IO)
        {
            budgetDAO.getAllBudgets().toBudgets()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
        ) {
            if (budgets == null) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            } else {
                BudgetsList(navController)
            }
            ValidateBtn("Create a budget") { navController.navigate(BudgetScreen.Create.route) }
        }
    }
}

@Composable
fun BudgetsList(navController: NavHostController) {
    LazyColumn {
        items(budgets!!) { budget ->
            BudgetCard(navController = navController, budget)
        }
    }
}

@Composable
fun BudgetCard(navController: NavHostController, budget: Budget) {
    Column(
        modifier = Modifier
            .background(Color(0xFFFCFCFC), shape = RoundedCornerShape(16.dp))
            .width(650.dp)
            .height(180.dp)
            .padding(16.dp)
            .clickable { navController.navigate(route = "DETAILS/$budget") }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Top, modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFF1F1FA),
                    shape = RoundedCornerShape(size = 32.dp)
                )
                .width(109.dp)
                .height(33.dp)
                .background(color = Color(0xFFFCFCFC), shape = RoundedCornerShape(size = 32.dp))
                .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = budget.category, fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF212325)
            )
        }

        Text(
            text = "Remaining : ${budget.amount - budget.spent}€",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF0D0E0F)
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = Color.Blue,
            backgroundColor = Color.Gray,
            progress = budget.spent / budget.amount
        )
        Text(
            text = "${budget.spent} of ${budget.amount}€",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF91919F)
        )
        Text(
            text = "${budget.id}",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF91919F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetPreview() {
    BudgetScreen(rememberNavController())
}