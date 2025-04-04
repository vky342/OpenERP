package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.PurchaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_purchase_VM @Inject constructor(private val purchaseRepo: PurchaseRepo, private val accountRepo: AccountRepo) : ViewModel() {

    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    init {
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    val samplePurchaseEntries = listOf(
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 10,
                entryPrice = 15.50,
                discount = 2.50,
                finalPrice = 13.00,
                itemName = "Notebook",
                purchaseId = 101
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 5,
                entryPrice = 50.00,
                discount = 0.0,
                finalPrice = 50.00,
                itemName = "Wireless Mouse",
                purchaseId = 102
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 20,
                entryPrice = 2.75,
                discount = 1.50,
                finalPrice = 1.25,
                itemName = "Pen",
                purchaseId = 103
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 8,
                entryPrice = 100.00,
                discount = 10.00,
                finalPrice = 90.00,
                itemName = "Headphones",
                purchaseId = 104
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 15,
                entryPrice = 30.00,
                discount = 5.00,
                finalPrice = 25.00,
                itemName = "USB Drive",
                purchaseId = 105
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 3,
                entryPrice = 250.00,
                discount = 25.00,
                finalPrice = 225.00,
                itemName = "External Hard Drive",
                purchaseId = 106
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 12,
                entryPrice = 7.50,
                discount = 0.75,
                finalPrice = 6.75,
                itemName = "Notebook",
                purchaseId = 107
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 2,
                entryPrice = 500.00,
                discount = 50.00,
                finalPrice = 450.00,
                itemName = "Monitor",
                purchaseId = 108
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 7,
                entryPrice = 35.00,
                discount = 0.0,
                finalPrice = 35.00,
                itemName = "Keyboard",
                purchaseId = 109
            ),
            PurchaseEntry(
                entryId = 0,
                entryQuantity = 6,
                entryPrice = 60.00,
                discount = 6.00,
                finalPrice = 54.00,
                itemName = "Bluetooth Speaker",
                purchaseId = 110
            )
        )


    private fun Save_Purchase(account_name : String, purchase : Purcahase, list_of_purEntries : List<PurchaseEntry>){

        /*
        *
        * name should be passed with Pre-Checking
        * purchase should contain id = 0, and ledgerId = 0
        * list_of_purEntries should contain entryId = 0 and purchaseId = 0, it will be set automatically
        * only name is to be passed and other data will be set automatically , as per the latest Purchase Id and account's Ledger Id.
        *
        */

        viewModelScope.launch (Dispatchers.IO){

            purchaseRepo.AddPurchase(
                account_name = account_name,
                purchase = purchase,
                list_of_purchaseEntry = list_of_purEntries
            )

        }
    }



    fun add_purchase(name : String, purchase: Purcahase, listOfEntry: List<PurchaseEntry>){

        Save_Purchase(name,purchase,listOfEntry)

    }

//    fun ini_test(){
//        Save_Purchase(account_name = "kunal", purchase = Purcahase(0,"something",0,1000), list_of_purEntries = samplePurchaseEntries)
//    }

}