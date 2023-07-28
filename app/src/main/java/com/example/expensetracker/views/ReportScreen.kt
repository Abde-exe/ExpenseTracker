package com.example.expensetracker

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.expensetracker.api.getCurrencyData
import com.example.expensetracker.models.Budget
import com.example.expensetracker.models.toBudgetEntity
import com.example.expensetracker.views.ValidateBtn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ReportScreen(navController: NavController) {
    val sharedPref = LocalContext.current.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    val amount: MutableState<String> = remember {
        mutableStateOf("0")
    }
    val isEuro: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFFD3C4A)),
       verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AmountField(onAmountChange = { newAmount -> amount.value = newAmount }, amount.value, isEuro)
        ValidateBtn("Continue") {
            CoroutineScope(Dispatchers.IO).launch {
                editor.putFloat("totalBudget", amount.value.toFloat())
                editor.apply()
                withContext(Dispatchers.Main) {
                    navController.popBackStack()
                }
            }
        }
    }
}



