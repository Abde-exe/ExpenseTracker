package com.example.expensetracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpensesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {

            Text(
                "Today",
                fontWeight = FontWeight(600),
                fontSize = 18.sp,
                color = Color(0xFF0D0E0F),
                modifier = Modifier.padding(16.dp)
            )
            ExpensesList(3)
            Text(
                "Yesterday",
                fontWeight = FontWeight(600),
                fontSize = 18.sp,
                color = Color(0xFF0D0E0F),
                modifier = Modifier.padding(16.dp)
            )
            ExpensesList(3)
        }
    }
}

@Composable
fun ExpensesList(count: Int) {
    LazyColumn {
        items(count) {
            ExpenseItem()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ExpensesScreen()
}