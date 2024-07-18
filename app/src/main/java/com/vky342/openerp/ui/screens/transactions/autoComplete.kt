package com.vky342.openerp.ui.screens.transactions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Preview
@Composable
fun AutoCompleteName() {

    val categories = listOf(
        "Food",
        "Beverages",
        "Sports",
        "Learning",
        "Travel",
        "Rent",
        "Bills",
        "Fees",
        "Others",
    )

    var category by remember {
        mutableStateOf("")
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    expanded = false
                }
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .onGloballyPositioned { coordinates ->
//                            textFieldSize = coordinates.size.toSize()
//                        },
                    value = category,
                    onValueChange = {
                        category = it
                        expanded = true
                    },
                    colors = TextFieldDefaults.colors()
                    ,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 13.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {expanded = false}),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
            Column (modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterHorizontally)){
                if (expanded){
                    Popup(alignment = Alignment.BottomCenter,properties = PopupProperties(excludeFromSystemGesture = true), onDismissRequest = {expanded = false}) {

                        LazyColumn(
                            modifier = Modifier.heightIn(max = 150.dp),
                        ) {

                            if (category.isNotEmpty()) {
                                items(
                                    categories.filter {
                                        it.lowercase()
                                            .contains(category.lowercase()) || it.lowercase()
                                            .contains("others")
                                    }
                                        .sorted()
                                ) {
                                    PartsItems(name = it) { name ->
                                        category = name
                                        expanded = false
                                    }
                                }
                            } else {
                                items(
                                    categories.sorted()
                                ) {
                                    PartsItems(name = it) { name ->
                                        category = name
                                        expanded = false
                                    }
                                }
                            }

                        }

                    }
                }
            }


//            AnimatedVisibility(visible = expanded) {
//
//                Card(
//                    modifier = Modifier
//                        .padding(horizontal = 5.dp)
//                        .width(textFieldSize.width.dp),
//                    shape = CardDefaults.shape
//                ) {
//
//                    LazyColumn(
//                        modifier = Modifier.heightIn(max = 150.dp),
//                    ) {
//
//                        if (category.isNotEmpty()) {
//                            items(
//                                categories.filter {
//                                    it.lowercase()
//                                        .contains(category.lowercase()) || it.lowercase()
//                                        .contains("others")
//                                }
//                                    .sorted()
//                            ) {
//                                PartsItems(name = it) { name ->
//                                    category = name
//                                    expanded = false
//                                }
//                            }
//                        } else {
//                            items(
//                                categories.sorted()
//                            ) {
//                                PartsItems(name = it) { name ->
//                                    category = name
//                                    expanded = false
//                                }
//                            }
//                        }
//
//                    }
//
//                }
//            }



    }


}

@Composable
fun PartsItems(
    name: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(name)
            }
    ) {
        Text(text = name, fontSize = 16.sp)
    }

}