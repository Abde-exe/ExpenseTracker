package com.example.expensetracker.views.Budget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.AmountField
import com.example.expensetracker.CategorySpinner
import com.example.expensetracker.Constants
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.toBudgetEntity
import com.example.expensetracker.views.ValidateBtn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var textColor = Color(0xFFFCFCFC)


@Composable
fun BudgetCreate(navController: NavController) {
    val database = AppDatabaseSingleton.getInstance(LocalContext.current)

    val bgColor = Color(0xFF7F3DFF)
    val amount: MutableState<String> = remember {
        mutableStateOf("0")
    }
    val category: MutableState<String> = remember {
        mutableStateOf( Constants.Categories.SHOPPING.categoryName)
    }
    val isEuro: MutableState<Boolean> = remember {
        mutableStateOf(true)
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
                contentDescription = "arrowBack",
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
        //Spacer(modifier = Modifier.height(250.dp))
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "How much do yo want to spend?",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFCFCFC),
                )
            )
            AmountField(onAmountChange = { newAmount -> amount.value = newAmount }, amount.value)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 64.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CategorySpinner(category.value) { newCategory -> category.value = newCategory }
                ValidateBtn("Continue") {
                    val newBudget = Budget(
                        id = 0,
                        amount = if (amount.value != "") amount.value.toFloat() else 0f,
                        currency = if (isEuro.value) Constants.Currencies.EUR.currencyName else Constants.Currencies.TRY.currencyName,
                        spent = 0f,
                        category = category.value
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        if (newBudget.amount > 0f) database.budgetDao()
                            .insert(newBudget.toBudgetEntity())
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CreatePreview() {
    BudgetCreate(rememberNavController())
}