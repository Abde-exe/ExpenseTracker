package com.example.expensetracker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.expensetracker.views.BottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesScreen(onClick: () -> Unit) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
        sheetElevation = 20.dp,
        scaffoldState = scaffoldState,
        sheetBackgroundColor = Color.White,
        sheetContent = {
            BottomSheet()
        }) {
        Sections(onClick, sheetState, scope)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Sections(onClick: () -> Unit, sheetState: BottomSheetState, scope: CoroutineScope) {
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
        Column() {

            Text(
                "Today",
                fontWeight = FontWeight(600),
                fontSize = 18.sp,
                color = Color(0xFF0D0E0F),
                modifier = Modifier.padding(16.dp)
            )
            ExpensesList(3, onClick)
            Text(
                "Yesterday",
                fontWeight = FontWeight(600),
                fontSize = 18.sp,
                color = Color(0xFF0D0E0F),
                modifier = Modifier.padding(16.dp)
            )
            ExpensesList(3, onClick)


        }

    }
}

@Composable
fun ExpensesList(count: Int, onClick: () -> Unit) {
    LazyColumn {
        items(count) {
            ExpenseItem(onClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ExpensesScreen({ print("z") })
}