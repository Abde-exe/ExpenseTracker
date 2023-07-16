package com.example.expensetracker

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensetracker.database.AppDatabaseSingleton
import com.example.expensetracker.models.Expense
import com.example.expensetracker.models.toExpenses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

private val color_item_bg = Color(0xfff1f1fa)


@Composable
fun HomeContent(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CurrencyChange()
            Balance(1000.00f, 10.00f)
            TodayExpenses(navController)
        }
    }
}

//components

@Composable
fun CurrencyChange() {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Exchange",
            color = Color(0xFF91919F),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "1€ = ",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "28,39₺",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,

                )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "1₺ = ",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "0,035€",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,

                )
        }
    }


}

@Composable
fun Balance(amount: Float, spent: Float) {
    Text(
        text = "Budget",
        color = Color(0xFF91919F),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = "$amount €",
        fontSize = 40.sp,
        fontWeight = FontWeight(600),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        //Remaining
        Row(
            modifier = Modifier
                .padding(0.dp)
                .width(164.dp)
                .height(80.dp)
                .background(Color(0xff00a86b), shape = RoundedCornerShape(28.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_remaining),
                contentDescription = "remaining"
            )
            Column {

                Text(
                    text = "Remaining",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFCFCFC)
                )
                Text(
                    text = "${amount - spent} €",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFCFCFC)
                )
            }

        }
        //Spent
        Row(
            modifier = Modifier
                .padding(0.dp)
                .width(164.dp)
                .height(80.dp)
                .background(Color(0xfffd3c4a), shape = RoundedCornerShape(28.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_spent),
                contentDescription = "remaining"
            )
            Column {

                Text(
                    text = "Spent",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFCFCFC)
                )
                Text(
                    text = "$spent €",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFCFCFC)
                )
            }

        }
    }
}

@Composable
fun TodayExpenses(navController: NavController) {
    lateinit var expenses: List<Expense>

    var database = AppDatabaseSingleton.getInstance(LocalContext.current)
    LaunchedEffect(Unit) {
        expenses = withContext(Dispatchers.IO) {
            database.expenseDao().getAllExpenses().toExpenses()
        }
        Log.d("test", expenses[0].amount.toString())
    }
    Text(
        "Today's expenses",
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
        color = Color(0xFF0D0E0F),
        modifier = Modifier.padding(8.dp)
    )

    ExpensesList(navController, expenses)
}


//subcomponents
@Composable
fun ExpenseItem(onClick: () -> Unit, expense: Expense) {
    Row(
        modifier = Modifier
            .padding(24.dp, 8.dp)
            .height(100.dp)
            .background(color_item_bg, shape = RoundedCornerShape(24.dp))
            .clickable { onClick() }

    )
    {
        Row(modifier = Modifier.padding(16.dp)) {
            val icon = painterResource(id = R.drawable.ic_expense_type)
            Image(
                painter = icon, contentDescription = "expenseItem",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                Column(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = expense.category,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF292B2D)
                    )
                    Text(
                        text = expense.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF91919F)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val currencySymbol = Constants.Currencies.valueOf(expense.currency).symbol
                    Text(
                        text = "${expense.amount} $currencySymbol",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFD3C4A),
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val formattedDate =
                        SimpleDateFormat("HH:mm", Locale.getDefault()).format(expense.date)
                    Text(
                        text = formattedDate,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF91919F),
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}



