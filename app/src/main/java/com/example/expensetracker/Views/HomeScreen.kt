package com.example.expensetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Balance(1000.00f, 10.00f)
            TodaysExpenses()
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
            Column() {

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
            Column() {

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
fun TodaysExpenses(){
    Text(
        "Today's expenses",
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
        color = Color(0xFF0D0E0F),
        )
    ExpensesList(count = 3)
}


@Composable
fun ExpenseItem() {
    Row(
        modifier = Modifier
            .padding(24.dp, 8.dp)
            .height(100.dp)
            .background(Color(0xFFFCFCFC), shape = RoundedCornerShape(24.dp))

    )
    {
        Row(modifier = Modifier.padding(16.dp)) {
            //  val icon = painterResource(id = R.drawable.ic_expense_type)
            /* Image(
                 painter = icon, contentDescription = "expenseItem",
                 modifier = Modifier
                     .height(70.dp)
                     .width(70.dp))*/

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
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Shopping",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF292B2D)
                    )
                    Text(
                        text = "Buy some grocery",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF91919F)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "- $120",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFD3C4A)
                    )
                    Text(
                        text = "10:00 AM",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF91919F)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
