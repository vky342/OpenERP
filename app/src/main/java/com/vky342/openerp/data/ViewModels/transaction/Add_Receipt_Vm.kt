package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
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
    val receiptID : MutableState<Int> = mutableStateOf(0)

    val oldReceipt : MutableLiveData<Receipt> = MutableLiveData(Receipt(0,"",0.0,0))
    val oldLedger : MutableLiveData<Ledger> = MutableLiveData(Ledger(0,0.0,""))

    init {
        viewModelScope.launch {
            receiptRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
        viewModelScope.launch {
            receiptID.value = receiptRepo.getLatestReceiptID()
            Log.d("STATUS", "fetched latest receipt ID")
        }
    }

    private fun add_receipt(receipt: Receipt, amount: Double, name: String){
        viewModelScope.launch (Dispatchers.IO) {
            receiptRepo.AddReceipt(receipt,amount,name)
            reloadReceipt()
        }
    }

    fun AddReceipt(name : String, Date : String, amount : Double, id : Int = 0) : Boolean{
        if (name == "" || Date == "" || amount == 0.0){
            return false
        }
        try {
            add_receipt(receipt = Receipt(id,Date,ledgerId = 0, receiptAmount = amount),amount,name)
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

    private fun reloadReceipt() {
        viewModelScope.launch {
            receiptID.value = receiptRepo.getLatestReceiptID()
            Log.d("DEBUG",receiptID.value.toString())
        }

    }

    fun getReceiptByID(ID : Int) {
        try {
            viewModelScope.launch {
                oldReceipt.value = receiptRepo.getReceiptByID(ID = ID)
                if (oldReceipt.value != Payment(0,"",0.0,0)){
                    oldLedger.value = receiptRepo.getLedgerByID(oldReceipt.value!!.ledgerId)
                }
            }
        }catch (e : Exception) {
            Log.e("ERROR"," unable to get receipt by ID")
        }
    }

    fun getRecentReceipt() {
        try {
            viewModelScope.launch {
                val pid = receiptRepo.getLatestReceiptID()
                if (pid != 0){
                    oldReceipt.value = receiptRepo.getReceiptByID(ID = pid)
                    if (oldReceipt.value != Payment(0,"",0.0,0)){
                        oldLedger.value = receiptRepo.getLedgerByID(oldReceipt.value!!.ledgerId)
                    }
                }else{
                    Log.d("DEBUG", "getRecentReceipt: no recent receipt found")
                }
            }
        }catch (e : Exception) {
            Log.e("ERROR"," unable to get receipt by ID")
        }
    }

    fun updateReceipt(name : String, Date: String, amount: Double){
        // deleting the old payment
        viewModelScope.launch {
            receiptRepo.deleteReceipt(oldReceipt.value!!, oldLedger.value!!)
            AddReceipt(name,Date,amount, id = oldReceipt.value!!.receiptId)
            oldReceipt.value = Receipt(0,"",0.0,0)
            oldLedger.value = Ledger(0,0.0,"")
        }
    }

}