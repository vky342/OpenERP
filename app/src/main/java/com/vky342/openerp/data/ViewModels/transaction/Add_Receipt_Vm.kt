package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.Repositories.ReceiptRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_Receipt_Vm @Inject constructor  (private val receiptRepo: ReceiptRepo) : ViewModel() {
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val balance : MutableState<Double> = mutableStateOf(0.0)

    init {
        viewModelScope.launch {
            receiptRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    private fun add_receipt(receipt: Receipt, amount: Double, name: String){
        viewModelScope.launch (Dispatchers.IO) {
            receiptRepo.AddReceipt(receipt,amount,name)
        }

    }

    fun AddReceipt(name : String, Date : String, amount : Double) : Boolean{

        if (name == "" || Date == "" || amount == 0.0){
            return false
        }

        try {
            add_receipt(receipt = Receipt(0,Date,ledgerId = 0, receiptAmount = amount),amount,name)
            return true
        }
        catch (e : Exception){
            Log.e("ERROR", "AddReceipt: ",e)
            return false
        }

    }

    fun load_balance(name: String){
        viewModelScope.launch {
            balance.value = receiptRepo.getBalance(name)
        }
    }

}