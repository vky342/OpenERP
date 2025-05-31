package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.LedgerRepo
import com.vky342.openerp.data.Repositories.PaymentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_Payment_Vm @Inject constructor  (private val paymentRepo: PaymentRepo) : ViewModel() {
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val balance : MutableState<Double> = mutableStateOf(0.0)
    val paymentID : MutableState<Int> = mutableStateOf(0)

    val oldPayment : MutableLiveData<Payment> = MutableLiveData(Payment(0,"",0.0,0))
    val oldLedger : MutableLiveData<Ledger> = MutableLiveData(Ledger(0,0.0,""))

    init {
        viewModelScope.launch {
            paymentRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }

        viewModelScope.launch {
            paymentID.value = paymentRepo.getLatestPaymentID()
            Log.d("STATUS", "fetched latest payment ID")
        }
    }

    private fun add_payment(payment: Payment, amount: Double, name: String){
        viewModelScope.launch (Dispatchers.IO) {
            paymentRepo.AddPayment(payment,amount,name)
            reloadPayment()
        }
    }

    fun AddPayment(name : String, Date : String, amount : Double, id : Int = 0) : Boolean{
        if (name == "" || Date == "" || amount == 0.0){
            return false
        }
        try {
            add_payment(payment = Payment(id,Date,ledgerId = 0, paymentAmount = amount),amount,name)
            return true
        }
        catch (e : Exception){
            Log.e("ERROR", "AddPayment: ",e)
            return false
        }
    }

    fun load_balance(name: String){
        viewModelScope.launch {
            balance.value = paymentRepo.getBalance(name)
        }
    }

    private fun reloadPayment() {
        viewModelScope.launch {
            paymentID.value = paymentRepo.getLatestPaymentID()
            Log.d("DEBUG",paymentID.value.toString())
        }

    }

    fun getPaymentByID(ID : Int) {
        try {
            viewModelScope.launch {
                oldPayment.value = paymentRepo.getPaymentByID(ID = ID)
                if (oldPayment.value != Payment(0,"",0.0,0)){
                    oldLedger.value = paymentRepo.getLedgerByID(oldPayment.value!!.ledgerId)
                }
            }
        }catch (e : Exception) {
            Log.e("ERROR"," unable to get payment by ID")
        }
    }

    fun updatePayment(name : String, Date: String, amount: Double){
        // deleting the old payment
        viewModelScope.launch {
            paymentRepo.deletePayment(oldPayment.value!!, oldLedger.value!!)
            AddPayment(name,Date,amount, id = oldPayment.value!!.paymentId)
            oldPayment.value = Payment(0,"",0.0,0)
            oldLedger.value = Ledger(0,0.0,"")
        }
    }

}