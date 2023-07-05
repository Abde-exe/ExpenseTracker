package com.example.expensetracker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

private var header = Color(0xFFFD3C4A)
private var textColor = Color(0xFFFCFCFC)

@Composable
fun NewScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(header)
    ) {
        Header(onClick)
        Form(onClick)
        Spacer(
            modifier = Modifier
                .height(50.dp)
        )


    }
}

@Composable
fun Header(onClick: () -> Unit) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
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
                text = "Expense", fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = textColor,
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = header,
            )

        }
        Column(
            modifier = Modifier
                .background(header, shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .fillMaxWidth()
                .height(280.dp)
                .padding(24.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "How much ?", color = textColor, fontSize = 18.sp, modifier = Modifier.align(
                    Alignment.Start
                )
            )
            AmountField()
        }
    }
}

@Composable
fun Form(onClick: () -> Unit) {
    val category: MutableState<String> = remember {
        mutableStateOf("")
    }
    val title: MutableState<String> = remember {
        mutableStateOf("")
    }
    val type: MutableState<String> = remember {
        mutableStateOf("")
    }
    val photoUri: MutableState<Uri?> = remember { mutableStateOf(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TitleField(title)
            CategorySpinner(category)
            TypeSpinner(type)
            ImagePicker(photoUri)
            ValidateBtn(onClick)

        }
    }
}

@Composable
fun TitleField(title: MutableState<String>) {
    OutlinedTextField(
        value = title.value,
        onValueChange = { newText: String -> title.value = newText },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF1F1FA),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .width(343.dp)
            .height(56.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
        ),
        placeholder = { Text(text = "Title") },
    )
}


@Composable
fun ValidateBtn(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .width(343.dp)
            .height(56.dp)
            .background(
                color = Color(0xFF7F3DFF),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)

    ) {
        Text(
            text = "Continue",
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFFCFCFC),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun AmountField() {
    var text by remember { mutableStateOf("0") }
    var isEuro: Boolean by remember { mutableStateOf(true) }
    var currencyDrawable: Int by remember { mutableStateOf(R.drawable.baseline_euro_24) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(contentPadding = PaddingValues(0.dp), onClick = {
            if (isEuro) {
                currencyDrawable = R.drawable.baseline_currency_lira_24
                isEuro = false
            } else {
                currencyDrawable = R.drawable.baseline_euro_24
                isEuro = true
            }
        }, colors = ButtonDefaults.buttonColors(header)) {
            Icon(
                painterResource(id = currencyDrawable),
                contentDescription = "Euro Icon",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
        }
        OutlinedTextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newText -> text = newText },
            modifier = Modifier
                .background(header),
            textStyle = TextStyle(fontSize = 77.sp, color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = header,
                unfocusedBorderColor = header,
            )
        )
    }
}

enum class CategoryItems(val title: String) {
    ITEM_1("Shopping"),
    ITEM_2("Transportation"),
    ITEM_3("Accommodation"),
    ITEM_4("Restaurants"),
    ITEM_5("Activities"),
    ITEM_6("Clothes"),
    ITEM_7("Not specified"),
}

@Composable
fun CategorySpinner(category: MutableState<String>) {
    val selectedOption = remember { mutableStateOf(CategoryItems.ITEM_1) }
    val options = remember {
        mutableStateListOf(
            CategoryItems.ITEM_1,
            CategoryItems.ITEM_2,
            CategoryItems.ITEM_3,
            CategoryItems.ITEM_4,
            CategoryItems.ITEM_5,
            CategoryItems.ITEM_6,
            CategoryItems.ITEM_7,
        )
    }
    val expandedState = remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF1F1FA),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp, 8.dp)
            .clickable { expandedState.value = true },
    ) {

        Text(
            text = selectedOption.value.title,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Icon(painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "efe")
        DropdownMenu(
            expanded = expandedState.value,
            onDismissRequest = { expandedState.value = false }) {

            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedOption.value = option
                    expandedState.value = false
                    category.value = option.title
                }) {
                    Text(text = option.title)
                }
            }
        }
    }
}

enum class TypeItems(val title: String) {
    ITEM_1("Cash"),
    ITEM_2("Card"),
    ITEM_3("Transfer"),
}

@Composable
fun TypeSpinner(type: MutableState<String>) {
    val selectedOption = remember { mutableStateOf(TypeItems.ITEM_1) }
    val options = remember {
        mutableStateListOf(
            TypeItems.ITEM_1,
            TypeItems.ITEM_2,
            TypeItems.ITEM_3,
        )
    }
    val expandedState = remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF1F1FA),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp, 8.dp)
            .clickable { expandedState.value = true },
    ) {

        Text(
            text = selectedOption.value.title,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Icon(painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "efe")
        DropdownMenu(
            expanded = expandedState.value,
            onDismissRequest = { expandedState.value = false }) {

            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedOption.value = option
                    expandedState.value = false
                    type.value = option.title
                }) {
                    Text(text = option.title)
                }
            }
        }
    }

}

@Composable
fun ImagePicker(photoUri: MutableState<Uri?>) {

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            photoUri.value = uri
        }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF1F1FA),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp, 8.dp)
            .clickable {
                launcher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
    ) {

        Text(
            text = "Add attachment",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        if (photoUri.value != null) {
            //Use Coil to display the selected image
            val painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = photoUri.value)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null

            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun NewPreview() {
    NewScreen({})
}