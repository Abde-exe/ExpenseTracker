package com.example.expensetracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ValidateBtn(text: String, onClick: () -> Unit) {

    Button(
        onClick = { onClick()},
        modifier = Modifier
            .width(343.dp)
            .height(56.dp)
            .background(
                color = Color(0xFF7F3DFF), shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7F3DFF))

    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFFCFCFC),
            textAlign = TextAlign.Center
        )

    }
}
