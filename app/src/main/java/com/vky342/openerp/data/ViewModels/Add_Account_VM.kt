package com.vky342.openerp.data.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class Add_Account_VM @Inject constructor( private val accountRepo: AccountRepo): ViewModel(){

    private val listOfAllAccountAsState : StateFlow<List_of_all_account_as_state> = accountRepo.get_all_account()
        .map { List_of_all_account_as_state(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = List_of_all_account_as_state()
        )


    private fun get_All_existing_names() : List<String>{
        val list_of_account = listOfAllAccountAsState.value
        val list_of_names : List<String> = listOf()
        for (account in list_of_account.account_list){
            account.name.map { list_of_names.plus(it) }
        }
        return list_of_names
    }


    private fun isValidName(name: String): Boolean {
        // Check if the name is empty or blank
        if (name.isBlank()) {
            return false
        }

        // Check if the name starts or ends with a space or hyphen
        if (name.startsWith(" ") || name.startsWith("-") || name.endsWith(" ") || name.endsWith("-")) {
            return false
        }

        // Check if the name contains only valid characters (alphabetic, space, hyphen)
        if (!name.all { it.isLetter() || it == ' ' || it == '-' }) {
            return false
        }

        if (name in get_All_existing_names()){
            return false
        }

        return true
    }


     private fun isValidContact(contact: Int): Boolean {
            // Convert the contact number to a string
        val contactStr = contact.toString()

            // Check if the contact is exactly 10 digits long
        if (contactStr.length != 10) {
            return false
        }

            // Ensure the contact number does not start with a '0'
        if (contactStr.startsWith("0")) {
            return false
        }

        return true
    }

    private fun isValidBalance(number : Int) : Boolean{
        if (number < 0){
            return false
        }
        return true
    }


    private fun isValidAccount(account: Account) : Boolean{

        if (!isValidName(account.name)) {
            return false
        }

        if (!isValidContact(account.contact)){
            return false
        }

        if (!isValidBalance(account.StartingBalance)){
            return false
        }

        return true

    }

     fun add_account(account: Account){

         if (!isValidAccount(account)){
             Log.e("VALIDITY", "account is invalid")
         }

         viewModelScope.launch {
             accountRepo.saveAccount(account)
             accountRepo.createLedger(Ledger(ledgerId = 0, ledgerNetBalance = account.StartingBalance, accountNameFk = account.name))

         }

    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class List_of_all_account_as_state(
    val account_list : List<Account> = listOf()
)