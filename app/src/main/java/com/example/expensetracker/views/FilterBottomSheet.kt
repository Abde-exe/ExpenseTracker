package com.example.expensetracker.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetracker.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet() {
    Column(
        modifier = Modifier
            .heightIn(max = 600.dp)
            .fillMaxSize()//Do this to make sheet expandable
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(//Using this to create a kind of Icon that tells the user that the sheet is expandable
            modifier = Modifier
                .width(36.dp)
                .height(4.dp)
                .background(color = Color(0xFFD3BDFF))
        )
        Spacer(//Another spacer to add a space
            modifier = Modifier.height(20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Filter Expense", fontSize = 16.sp, fontWeight = FontWeight(600)
            )
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(size = 40.dp),
                elevation = ButtonDefaults.elevation(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEEE5FF)),
            ) {
                Text(text = "Reset", color = Color(0xFF7F3DFF))
            }

        }
        FilterSection()
        SortingSection()
        CategorySection()
        ValidateBtn("Apply",{})
    }
}


@Composable
fun FilterSection() {
    Text(
        text = "Filter By",
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)

    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(size = 40.dp),
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5))
        ) {
            Text(text = "Cash", color = Color(0xFF7F3DFF), fontSize = 14.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(size = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEEE5FF)),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5))
        ) {
            Text(text = "Card", color = Color(0xFF7F3DFF))
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(size = 40.dp),
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5))
        ) {
            Text(text = "Transfer", color = Color(0xFF7F3DFF))
        }
    }
}

@Composable
fun SortingSection() {
    Text(
        text = "Sort By",
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)

    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(size = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5)),
            elevation = ButtonDefaults.elevation(0.dp),
            modifier = Modifier

        ) {
            Text(text = "Highest", color = Color(0xFF7F3DFF), fontSize = 14.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(size = 40.dp),
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5)),
        ) {
            Text(text = "Lowest", color = Color(0xFF7F3DFF))
        }
        Button(
            onClick = { /*TODO*/ },
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(size = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5)),
        ) {
            Text(text = "Newest", color = Color(0xFF7F3DFF))
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(size = 40.dp),
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE3E5E5)),
        ) {
            Text(text = "Oldest", color = Color(0xFF7F3DFF))
        }
    }
}

@Composable
fun CategorySection() {
    Text(
        text = "Category",
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Choose Category", fontSize = 16.sp,
            fontWeight = FontWeight(500),
        )
        Button(
            onClick = { /*TODO*/ },
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                text = "0 Selected",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF91919F)
            )
            Icon(painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "e")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BottomSheet()
}