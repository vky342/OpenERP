package com.vky342.openerp.data.ViewModels.Ledger


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.LedgerRepo
import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LedgerVm @Inject constructor( private val ledgerRepo: LedgerRepo, private val accountRepo: AccountRepo): ViewModel(){

    var selectedAccount : Account = Account(0,"","","","")
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    init {
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    private suspend fun PrivategetSpecificAccountLedger() : List<AccountLedgerItem >{
        if (selectedAccount == Account(0,"","","","")){
            return emptyList()
        }
        return ledgerRepo.getLedgerItemList(selectedAccount)
    }

    suspend fun getSpecificAccountLedger(): List<AccountLedgerItem> {
        return try {
            withContext(Dispatchers.IO) {
                PrivategetSpecificAccountLedger()
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Ledger Repo was unable to load account ledger: ${e.message}")
            emptyList()
        }
    }

    suspend fun getAccountBalance() : Double{
        return try {
            withContext(Dispatchers.IO) {
                ledgerRepo.getBalanceOfSpecificAccount(selectedAccount.name)
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Ledger Repo was unable to load account Balance: ${e.message}")
            0.0
        }
    }

    fun reset() : List<AccountLedgerItem>{
        selectedAccount = Account(0,"","","","")
        return listOf<AccountLedgerItem>()
    }
}