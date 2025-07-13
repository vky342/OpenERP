package com.vky342.openerp.ui.screens.HOMES

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vky342.openerp.data.ViewModels.HomeViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.InventoryScreens
import com.vky342.openerp.ui.Graphs.LedgerScreens
import com.vky342.openerp.ui.Graphs.TransactionScreen
import com.vky342.openerp.ui.theme.amount_stat_border_color
import com.vky342.openerp.ui.theme.amount_text_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_txt_color
import com.vky342.openerp.ui.theme.card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_border_color
import com.vky342.openerp.ui.theme.edit_item_card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_container_colour
import com.vky342.openerp.ui.theme.edit_item_content_color
import com.vky342.openerp.ui.theme.middle_spacer_color
import com.vky342.openerp.ui.theme.sale_button_background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.ui.theme.sale_icon_color
import com.vky342.openerp.ui.theme.search_item_border_color
import com.vky342.openerp.ui.theme.search_item_card_shadow_color
import com.vky342.openerp.ui.theme.search_item_container_colour
import com.vky342.openerp.ui.theme.search_item_content_color
import com.vky342.openerp.ui.theme.search_item_focused_container_colour
import com.vky342.openerp.ui.theme.shadow_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour
import com.vky342.openerp.utility.backupDatabaseToUri
import com.vky342.openerp.utility.restoreDatabaseFromUri


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(navController.getBackStackEntry("HomeScreen"))) {

    homeViewModel.setData()

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.05
    val context = LocalContext.current

    val saleData = homeViewModel.todaySaleData.observeAsState(0.0)
    val receiptData = homeViewModel.todayReceiptData.observeAsState(0.0)

    val restorePopUpEnabled = remember { mutableStateOf(false) }

    // SAF Launchers inside composable!
    val backupLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/vnd.sqlite3")) { uri: Uri? ->
        uri?.let {
            val success = backupDatabaseToUri(context, it)
            Toast.makeText(context, if (success) "Backup done" else "Backup failed", Toast.LENGTH_SHORT).show()
        }
    }

    val restoreLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            val success = restoreDatabaseFromUri(context, it)
            Toast.makeText(context, if (success) "Restore done" else "Restore failed", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {
        Column(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {
            // Variable Amount Row (20% Height → Adjusted to fixed 120.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = (sidePadding + 7).dp)
                        .shadow(
                            elevation = 4.dp,
                            spotColor = card_shadow_color,
                            ambientColor = card_shadow_color,
                            shape = CircleShape.copy(CornerSize(20f))
                        )
                        .background(
                            color = var_amount_row_colour,
                            shape = CircleShape.copy(
                                topStart = CornerSize(20f),
                                topEnd = CornerSize(20f),
                                bottomStart = CornerSize(20f),
                                bottomEnd = CornerSize(20f)
                            )
                        )
                        .border(
                            shape = CircleShape.copy(
                                topStart = CornerSize(20f),
                                topEnd = CornerSize(20f),
                                bottomStart = CornerSize(20f),
                                bottomEnd = CornerSize(20f)
                            ),
                            border = BorderStroke(1.dp, amount_stat_border_color)
                        )
                ) {
                    VariableAmountRow(modifier = Modifier.align(Alignment.Center), sale = saleData.value,receiptData.value)
                }
            }

            // Edit Items Button (13% Height → Adjusted to fixed 80.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(20f),
                            ambientColor = edit_item_card_shadow_color,
                            spotColor = edit_item_card_shadow_color
                        )
                        .background(
                            color = edit_item_container_colour,
                            shape = RoundedCornerShape(20f)
                        )
                        .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                        .align(Alignment.Center)
                        .clickable{
                            navController.navigate(InventoryScreens.InventoryOptions)
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(5.dp),
                            tint = edit_item_content_color
                        )
                        Text(
                            text = "Inventory",
                            fontSize = 20.sp,
                            color = edit_item_content_color,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }

            // Account Ledger Button (13% Height → Adjusted to fixed 80.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(20f),
                            ambientColor = edit_item_card_shadow_color,
                            spotColor = edit_item_card_shadow_color
                        )
                        .background(
                            color = edit_item_container_colour,
                            shape = RoundedCornerShape(20f)
                        )
                        .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                        .align(Alignment.Center)
                        .clickable{
                            navController.navigate(LedgerScreens.AccountLedger)
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Create,
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(5.dp),
                            tint = edit_item_content_color
                        )
                        Text(
                            text = "Account Ledger",
                            fontSize = 20.sp,
                            color = edit_item_content_color,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(20f),
                            ambientColor = edit_item_card_shadow_color,
                            spotColor = edit_item_card_shadow_color
                        )
                        .background(
                            color = edit_item_container_colour,
                            shape = RoundedCornerShape(20f)
                        )
                        .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                        .align(Alignment.Center)
                        .clickable{
                            navController.navigate(Graph.ANALYTICS)
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(5.dp),
                            tint = edit_item_content_color
                        )
                        Text(
                            text = "Analytics",
                            fontSize = 20.sp,
                            color = edit_item_content_color,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }

            // Data backup and restore
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {

                Row(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f).align(Alignment.Center)){

                    // Backup Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(20f),
                                ambientColor = edit_item_card_shadow_color,
                                spotColor = edit_item_card_shadow_color
                            )
                            .background(
                                color = bottom_bar_selected_txt_color,
                                shape = RoundedCornerShape(20f)
                            )
                            .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                            .align(Alignment.CenterVertically)
                            .clickable{
                                // backup initiator
                                backupLauncher.launch("OpenERPDB_backup.db")
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(5.dp),
                                tint = edit_item_content_color
                            )
                            Text(
                                text = "Backup ☁️",
                                fontSize = 20.sp,
                                color = edit_item_content_color,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(56.dp).width(5.dp))

                    // Restore Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(20f),
                                ambientColor = edit_item_card_shadow_color,
                                spotColor = edit_item_card_shadow_color
                            )
                            .background(
                                color = bottom_bar_selected_txt_color,
                                shape = RoundedCornerShape(20f)
                            )
                            .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                            .align(Alignment.CenterVertically)
                            .clickable{
                                // restore initiator
                            restorePopUpEnabled.value = true

                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(5.dp),
                                tint = edit_item_content_color
                            )
                            Text(
                                text = "Restore 📲",
                                fontSize = 20.sp,
                                color = edit_item_content_color,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }
                }

            }

        }
        // Sale Button (Fixed Height: 60.dp)
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = sale_button_box_color)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {


            Box( modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.75f)
                .shadow(elevation = 4.dp, shape = CircleShape.copy(CornerSize(20f)))
                .background(color = sale_button_background_color, shape = RoundedCornerShape(
                    CornerSize(20f)))
                .clickable{
                    navController.navigate(TransactionScreen.AddSale)
                }){
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    tint = sale_icon_color,
                    modifier = Modifier.size(50.dp).align(Alignment.Center)
                )
            }

        }

        if (restorePopUpEnabled.value == true){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray.copy(alpha = 0.5f))
                    .clickable { }) {

                // touch barrier

            }
            RestorePopUp(modifier = Modifier.align(Alignment.Center), onYes = {
                restorePopUpEnabled.value = false
                restoreLauncher.launch(arrayOf("*/*"))
                Toast.makeText(context, "Restore Successful", Toast.LENGTH_SHORT).show()
                                                                              },
                onNo = {
                    restorePopUpEnabled.value = false
                    Toast.makeText(context, "Restore Cancelled", Toast.LENGTH_SHORT).show()
            })
        }

    }

}




fun Calculate_color_of_pieces(int: Int) : Color{
    if (int < 6){
        return Color.Red
    }
    return Color.Green
}


@Composable
fun Searchbar(modifier: Modifier,current_value : String,label : String,onVC : (String)-> Unit , onClear : () -> Unit = {}) {


    TextField(
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(0.95f)
            .border(width = 1.dp, color = search_item_border_color, shape = CircleShape.copy(
                CornerSize(20f)))
            .shadow(elevation = 5.dp, shape = CircleShape.copy(CornerSize(20f)), ambientColor = search_item_card_shadow_color, spotColor = search_item_card_shadow_color),
        value = current_value,
        placeholder = { Text(label, color = search_item_content_color)},
        onValueChange = {
            onVC(it)

        },
        trailingIcon = { Icon(Icons.Default.Search,modifier = Modifier.clickable{
            onClear()
        }, contentDescription = "", tint = search_item_content_color)},
        colors = TextFieldDefaults.colors().copy(focusedContainerColor = search_item_focused_container_colour, unfocusedContainerColor = search_item_container_colour))

}

@Composable
fun AmountSection(title: String, amount: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            maxLines = 1 // Prevents multi-line wrapping
        )

        // Auto-adjusting font size for amount
        Box(
            modifier = Modifier
                .widthIn(min = 50.dp, max = 150.dp) // Ensures text has a fixed width range
        ) {
            AutoResizeText(
                text = "₹$amount",
                style = TextStyle(
                    shadow = Shadow(
                        color = shadow_color, // Shadow color
                        offset = Offset(0f, 4f), // Shadow offset (x, y)
                        blurRadius = 4f // Shadow blur radius
                    ),
                    fontWeight = FontWeight.Bold
                ),
                color = amount_text_color,
                textAlign = TextAlign.Center
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AutoResizeText(
    text: String,
    style: TextStyle,
    color: Color,
    textAlign: TextAlign,
    modifier: Modifier = Modifier
) {
    var textSize by remember { mutableStateOf(22.sp) } // Default font size

    BoxWithConstraints(modifier = modifier) {
        val maxWidth = constraints.maxWidth.toFloat()
        Text(
            text = text,
            style = style.copy(fontSize = textSize),
            color = color,
            textAlign = textAlign,
            modifier = Modifier.layout { measurable, constraints ->
                var newFontSize = textSize.value
                val placeable = measurable.measure(constraints)
                if (placeable.width > maxWidth) {
                    newFontSize *= maxWidth / placeable.width
                }
                textSize = newFontSize.coerceAtLeast(20f).sp
                layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
            }
        )
    }
}

@Composable
fun VariableAmountRow(modifier: Modifier = Modifier, sale : Double, receipt : Double) {
    // Mutable state for the amounts

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), // Fix height issue
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left section: Today's Sale
        AmountSection(
            title = "Today's Sale",
            amount = sale.toString(),
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )

        // Thin vertical line separator
        Box(
            modifier = modifier
                .width(3.dp)
                .height(40.dp) // Adjust height for better UI
                .background(middle_spacer_color)
                .shadow(elevation = 2.dp)
        )

        // Right section: Today's Receipts
        AmountSection(
            title = "Today's Receipts",
            amount = receipt.toString(),
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )
    }
}


@Preview
@Composable
fun RestorePopUp(modifier: Modifier = Modifier, onYes : () -> Unit = {}, onNo : () -> Unit = {}){
    Box (modifier = modifier.fillMaxWidth(0.9f).height(150.dp).background(color = title_color, shape = RoundedCornerShape(20f))){
        Column(modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.9f).align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(textAlign = TextAlign.Center, color = Color.White,text = "Restoring will erase the current data. Do you still want to continue?", modifier = Modifier.align(
                Alignment.CenterHorizontally).padding(vertical = 10.dp))
            Spacer(modifier = Modifier.height(30.dp).width(20.dp))
            Row (modifier = Modifier.fillMaxWidth().height(50.dp), horizontalArrangement = Arrangement.Center) {
                Box(modifier = Modifier.width(50.dp).height(40.dp).background(color = Color.White, shape = RoundedCornerShape(20f))
                    .clickable{
                        onYes()
                    }) {
                    Text("Yes", modifier = Modifier.align(Alignment.Center))
                }

                Spacer(modifier = Modifier.height(50.dp).width(50.dp))
                Box(modifier = Modifier.width(50.dp).height(40.dp).background(color = Color.White, shape = RoundedCornerShape(20f))
                    .clickable{
                        onNo()
                    }) {
                    Text("No", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}