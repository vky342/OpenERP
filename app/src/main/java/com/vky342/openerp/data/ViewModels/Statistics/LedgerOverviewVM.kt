package com.vky342.openerp.data.ViewModels.Statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Repositories.CashStatsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LedgerOverviewVM @Inject constructor( private val cashStatsRepo: CashStatsRepo): ViewModel(){
    val pendingPayableLedger : MutableLiveData<List<Ledger>> = MutableLiveData(listOf<Ledger>())
    val pendingReceivableLedger : MutableLiveData<List<Ledger>> = MutableLiveData(listOf<Ledger>())

    init {
        getPendingPayableLedgers()
        getPendingReceivableLedgers()
    }

    fun getPendingPayableLedgers(){
        viewModelScope.launch(Dispatchers.Main) {
            pendingPayableLedger.value = cashStatsRepo.getPendingPayableLedgers()
        }
    }

    fun getPendingReceivableLedgers(){
        viewModelScope.launch(Dispatchers.Main) {
            pendingReceivableLedger.value = cashStatsRepo.getPendingReceivableLedgers()
        }
    }
}