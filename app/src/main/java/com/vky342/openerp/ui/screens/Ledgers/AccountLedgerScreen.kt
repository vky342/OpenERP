package com.vky342.openerp.ui.screens.Ledgers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Ledger.LedgerVm
import com.vky342.openerp.ui.screens.ACCOUNTS.account_search_bar_for_edit_account
import com.vky342.openerp.ui.screens.HOMES.Searchbar
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour
import com.vky342.openerp.utility.calculateEffectiveBalancesWithLogic

data class AccountLedgerItem(
    val BillDate    : String,
    val BillType    : String,
    val BillAmount  : Double,
    val CashOrCredit : String = "",
    val effectiveBalance : Double = 0.0,
)


@Preview
@Composable
fun AccountLedgerScreen( viewModel: LedgerVm = hiltViewModel() ){

    val context : Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val accountLedgerItemList = remember { mutableStateOf(listOf<AccountLedgerItem>()) }

    val accountBalance = remember { mutableStateOf(0.0) }

    var old_Account by remember { mutableStateOf(Account(0,"","","","")) }

    var selectedOptionText by remember { mutableStateOf("") }

    val options = viewModel.old_Account_list.value

    val expanded = remember { mutableStateOf(false) }

    val updateLedgerListDisabled = remember { mutableStateOf(true) }

    // filter options based on text field value
    val filteringOptions = options.filter {
        it.name.contains(selectedOptionText, ignoreCase = true) || it.address.contains(selectedOptionText, ignoreCase = true) || it.contact.contains(selectedOptionText, ignoreCase = true)
    }

    LaunchedEffect(updateLedgerListDisabled.value) {
        if (updateLedgerListDisabled.value == false){
            Log.d("DEBUG","line 100")
            accountLedgerItemList.value = viewModel.getSpecificAccountLedger()
            accountLedgerItemList.value = calculateEffectiveBalancesWithLogic(accountLedgerItemList.value)
            accountBalance.value = viewModel.getAccountBalance()
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
                .fillMaxSize()
                .padding(vertical = 10.dp)
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Account Ledger", fontWeight = FontWeight.Bold,color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Account search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {

                account_search_bar_for_edit_account(onReset = {
                    updateLedgerListDisabled.value = true
                    old_Account = Account(0,"","","","")
                    selectedOptionText = ""
                    viewModel.selectedAccount = old_Account
                    Toast.makeText(context,"Select an account please", Toast.LENGTH_SHORT).show()
                    accountLedgerItemList.value = viewModel.reset()
                    accountBalance.value = 0.0
                },
                    enabled = updateLedgerListDisabled.value,modifier = Modifier
                    ,current_value = selectedOptionText
                    ,onVc = {
                        selectedOptionText = it
                        expanded.value = true
                    })
            }

            Box(modifier = Modifier.fillMaxWidth().height(40.dp)){
                Text(
                    "B a l a n c e",
                    fontSize = 20.sp, color = title_color,
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(color = var_amount_row_colour)) {
                if (accountBalance.value < 0){
                    val balance = accountBalance.value * -1
                    Text("$balance Dr", fontSize = 20.sp,modifier = Modifier.align(Alignment.Center))
                }
                else{
                    Text("${accountBalance.value} Cr", fontSize = 20.sp,modifier = Modifier.align(Alignment.Center))
                }

            }

            // List
            Box(modifier = Modifier.fillMaxWidth().heightIn(max = 750.dp).padding(vertical = 15.dp)) {
                accountLedger_list_table(modifier = Modifier.padding(horizontal = (sidePadding/2).dp),LegderItemList = accountLedgerItemList.value)
            }
        }

        // suggestions popUP
        if (expanded.value) {

            BackHandler (enabled = expanded.value) {
                expanded.value = false
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .padding(top = 150.dp)
                    .padding(horizontal = (sidePadding/2).dp)
                    .align(Alignment.TopCenter)
                    .shadow(elevation = 4.dp,shape = RoundedCornerShape(10f))
                    .background(color = Color.White, shape = RoundedCornerShape(10f))
            ){
                items (filteringOptions){ account ->
                    Text(text = account.name + " " + "(" +account.type+ ")",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier.padding(vertical = 1.dp, horizontal = 2.dp).fillMaxWidth()
                            .clickable{
                                old_Account = account
                                viewModel.selectedAccount = account
                                selectedOptionText = account.name + " " + "(" +account.type+ ")"
                                updateLedgerListDisabled.value = false
                                expanded.value= false
                                Toast.makeText(context,"Account selected", Toast.LENGTH_SHORT).show()
                            })
                }
            }
        }
    }
}

@Preview
@Composable
fun accountLedger_list_table(modifier: Modifier = Modifier, LegderItemList : List<AccountLedgerItem> = listOf()){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                // Bill Date
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.18f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Date", fontWeight = FontWeight.Bold,modifier = Modifier.align(Alignment.Center))
                }

                // Bill Type
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.23f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Bill", fontWeight = FontWeight.Bold,modifier = Modifier.align(Alignment.Center))
                }

                // Amount
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Amount",fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
        LazyColumn(reverseLayout = true,modifier = Modifier.fillMaxWidth()){
            itemsIndexed(LegderItemList){ index, item ->
                accountLedger_list_table_single_row(
                    item = item,
                    effectiveBalance = item.effectiveBalance
                )

            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
    }
}

@Preview
@Composable
fun accountLedger_list_table_single_row(modifier: Modifier = Modifier, item : AccountLedgerItem = AccountLedgerItem("12/6/2025","Purchase",1000.00), effectiveBalance : Double = 0.0) {

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray)
    )

    Row(modifier = modifier.fillMaxWidth().height(90.dp)) {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            Column (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    // Bill Date
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.18f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = item.BillDate,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Bill Type
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.23f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = item.BillType,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Bill Amount
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = "₹" + item.BillAmount.toString(),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    // Cash/Credit
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.7f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text =  "~ " + item.CashOrCredit,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.CenterStart)
                                .padding(start = 12.dp)
                        )
                    }


                    // Bill after effect balance
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color = Color.White)
                    ) {
                        if (effectiveBalance < 0){
                            val localBalance = effectiveBalance * -1
                            Text(
                                "~ Balance : ₹$localBalance Dr",
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                        else{
                            Text(
                                "~ Balance : ₹$effectiveBalance Cr",
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                    }
                }
            }

        }
    }
}

