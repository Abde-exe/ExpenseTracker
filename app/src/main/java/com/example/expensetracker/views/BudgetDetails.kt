package com.example.expensetracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BudgetDetails(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "arrowback",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Text(
                text = "Budget Detail", fontSize = 18.sp,
                fontWeight = FontWeight(600),
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
            )

        }

        Column(modifier = Modifier.fillMaxHeight().padding(32.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            Details()
            ValidateBtn("Edit") { navController.popBackStack() }
        }

    }
}

@Composable
fun Details() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFE3E5E5),
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .width(156.dp)
                .height(64.dp)
                .background(color = Color(0xFFFCFCFC), shape = RoundedCornerShape(size = 24.dp))
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Shopping",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF0D0E0F),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Text(
            text = "Remaining",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF0D0E0F),
            )
        )
        Text(
            text = "$0",
            style = TextStyle(
                fontSize = 64.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF0D0E0F),
            )
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = Color.Yellow,
            backgroundColor =
            Color.Blue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetDetailsPreview() {
    BudgetDetails(rememberNavController())
}