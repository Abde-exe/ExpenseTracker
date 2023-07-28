package com.example.expensetracker

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.models.Expense
import com.example.expensetracker.models.toBudgets
import com.example.expensetracker.models.toExpenses
import com.example.expensetracker.views.BottomSheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.exp




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesScreen(navController: NavController) {
    val database = AppDatabaseSingleton.getInstance(LocalContext.current)
    var expenses : List<Expense>  by remember{ mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
         withContext(Dispatchers.IO) {
             expenses =   database.expenseDao().getAllExpenses().toExpenses()
        }
    }
    if (expenses.isNotEmpty()) {
        Log.d("test", expenses[0].amount.toString())
    }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)


    BottomSheetScaffold(sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
        sheetElevation = 20.dp,
        scaffoldState = scaffoldState,
        sheetBackgroundColor = Color.White,
        sheetContent = {
            BottomSheet()
        }) {
        Sections(navController, sheetState, expenses)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Sections(navController: NavController, sheetState: BottomSheetState, expenses: List<Expense>) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .zIndex(1f)
                .padding(16.dp),
        ) {
            Button(onClick = {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            }, colors = ButtonDefaults.buttonColors(Color.White)) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "expandsheet",
                )

            }

        }
        Column {
            if (expenses == null) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            } else {
                Text(
                    "Today",
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp,
                    color = Color(0xFF0D0E0F),
                    modifier = Modifier.padding(16.dp)
                )
                ExpensesList(navController, expenses)
                /*Text(
                    "Yesterday",
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp,
                    color = Color(0xFF0D0E0F),
                    modifier = Modifier.padding(16.dp)
                )
                ExpensesList(navController, expenses)*/
            }

        }

    }
}

@Composable
fun ExpensesList(navController: NavController, expenses: List<Expense>) {
    LazyColumn {
        items(expenses) { expense ->
            ExpenseItem({ navController.navigate("EXPENSEDETAILS/$expense") }, expense)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ExpensesScreen(rememberNavController())
}