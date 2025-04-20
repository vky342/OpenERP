package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.LedgerRepo
import com.vky342.openerp.data.Repositories.PaymentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_Payment_Vm @Inject constructor  (private val paymentRepo: PaymentRepo) : ViewModel() {
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val balance : MutableState<Double> = mutableStateOf(0.0)

    init {
        viewModelScope.launch {
            paymentRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    private fun add_payment(payment: Payment, amount: Double, name: String){
        viewModelScope.launch (Dispatchers.IO) {
            paymentRepo.AddPayment(payment,amount,name)
        }

    }

    fun AddPayment(name : String, Date : String, amount : Double) : Boolean{

        if (name == "" || Date == "" || amount == 0.0){
            return false
        }

        try {
            add_payment(payment = Payment(0,Date,ledgerId = 0, paymentAmount = amount),amount,name)
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

}