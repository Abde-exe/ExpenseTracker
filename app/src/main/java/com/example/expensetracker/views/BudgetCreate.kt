package com.example.expensetracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.AmountField
import com.example.expensetracker.CategorySpinner

private var textColor = Color(0xFFFCFCFC)


@Composable
fun BudgetCreate(navController: NavHostController) {
    val bgColor = Color(0xFF7F3DFF)
    val category: MutableState<String> = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
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
                tint = textColor,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Text(
                text = "Create Budget", fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = textColor,
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = textColor,
            )

        }
        Spacer(modifier = Modifier.height(250.dp))
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "How much do yo want to spend?",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFCFCFC),
                )
            )
            AmountField()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp,16.dp,16.dp,64.dp).fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CategorySpinner(category)
                ValidateBtn("Continue") { navController.popBackStack() }
            }
        }
    }
}

@Preview
@Composable
fun CreatePreview() {
    BudgetCreate(rememberNavController())
}