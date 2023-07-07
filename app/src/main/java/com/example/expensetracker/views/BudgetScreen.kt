package com.example.expensetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.nav.BudgetScreen
import com.example.expensetracker.nav.Graph
import com.example.expensetracker.views.ValidateBtn


@Composable
fun BudgetScreen(navController: NavHostController) {
    val bgColor = Color(0xFF7F3DFF)
    var budgets: MutableList<String> = mutableListOf("")

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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
        ) {
            BudgetCard(navController)
            BudgetCard(navController)

            ValidateBtn("Create a budget") { navController.navigate(BudgetScreen.Create.route) }
        }
    }
}

@Composable
fun BudgetCard(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(Color(0xFFFCFCFC), shape = RoundedCornerShape(16.dp))
            .width(650.dp)
            .height(180.dp)
            .padding(16.dp)
            .clickable { navController.navigate(Graph.BUDGET) }
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
                text = "Shopping", fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF212325)
            )
        }

        Text(
            text = "Remaining $0",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF0D0E0F)
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = Color.Yellow,
            backgroundColor =
            Color.Blue
        )
        Text(
            text = "$1200 of $1000",
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