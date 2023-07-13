package com.example.expensetracker

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.models.Expense
import com.example.expensetracker.views.ValidateBtn
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

private var header = Color(0xFFFD3C4A)
private var textColor = Color(0xFFFCFCFC)
private var subTextColor = Color(0xFF91919F)

@Composable
fun ExpenseDetails(navController: NavHostController, expense: Expense) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header({ navController.popBackStack() }, expense)
        Description(expense.images?.get(0))
        Spacer(
            modifier = Modifier
                .height(50.dp)
        )
        ValidateBtn("Edit") { navController.popBackStack() }

    }
}

@Composable
fun Header(onClick: () -> Unit, expense: Expense) {
    Box() {
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
                    onClick()
                }
            )
            Text(
                text = "Expense Details", fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = textColor,
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = textColor,
            )

        }
        Column(
            modifier = Modifier
                .background(header, shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .fillMaxWidth()
                .height(280.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = expense.amount.toString(),
                color = textColor,
                fontSize = 48.sp,
                fontWeight = FontWeight(500)
            )
            Text(text = expense.title, color = textColor, fontSize = 16.sp)
            val formattedDate = SimpleDateFormat("dd/MM, HH:mm", Locale.getDefault()).format(expense.date)

            Text(text = formattedDate, color = textColor, fontSize = 13.sp)

        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
                )
                .height(70.dp)
                .width(345.dp)
                .padding(16.dp, 12.dp)
                .align(Alignment.BottomCenter)
        ) {
            Column {
                Text(
                    text = "Type", fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = subTextColor,
                )
                Text(
                    text = expense.paymentType, fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            }
            Column {
                Text(
                    text = "Category", fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = subTextColor,
                )
                Text(
                    text = expense.category, fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            }
            Column {
                Text(
                    text = "", fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = subTextColor,
                )
                Text(
                    text = "", fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            }
        }
    }
}

@Composable
fun Description(expenseImg : URL?) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxHeight(0.6f)
    ) {

        Text(
            "Description",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = subTextColor
        )

        Text(
            text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet.",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
        )
        Text(
            "Attachment",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = subTextColor
        )
        //val painter = expenseImg ?: painterResource(id = R.drawable.rectangle_207)
        val painter = painterResource(id = R.drawable.rectangle_207)
        Image(
            painter = painter,
            contentDescription = "imgAsset",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    ExpenseDetails(rememberNavController(), Constants.getExpenses()[0])
}