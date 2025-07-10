package com.vky342.openerp.data.ViewModels.Statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Repositories.CashStatsRepo
import com.vky342.openerp.utility.getCurrentMonthYear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashStatsVM @Inject constructor( private val cashStatsRepo: CashStatsRepo): ViewModel(){
    val totalCashInflow : MutableLiveData<Double> = MutableLiveData(0.0)
    val totalCashOutflow : MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPendingPayable : MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPendingReceivable : MutableLiveData<Double> = MutableLiveData(0.0)
    val monthlyCashInflow : MutableLiveData<Double> = MutableLiveData(0.0)
    val selectedMonthYearInflow : MutableLiveData<String> = MutableLiveData(getCurrentMonthYear())
    val monthlyCashOutflow : MutableLiveData<Double> = MutableLiveData(0.0)
    val selectedMonthYearOutflow : MutableLiveData<String> = MutableLiveData(getCurrentMonthYear())
    val pendingPayableLedger : MutableLiveData<List<Ledger>> = MutableLiveData(listOf<Ledger>())

    init {
        getCashInflow()
        getCashOutflow()
        getPendingPayable()
        getPendingReceivable()
        getMonthlyCashInflow()
        getMonthlyCashOutflow()
    }

    private fun getCashInflow(){
        viewModelScope.launch(Dispatchers.Main) {
            totalCashInflow.value = cashStatsRepo.getTotalCashInflow()
        }
    }

    private fun getCashOutflow(){
        viewModelScope.launch(Dispatchers.Main) {
            totalCashOutflow.value = cashStatsRepo.getTotalCashOutflow()
        }
    }

    private fun getPendingPayable(){
        viewModelScope.launch(Dispatchers.Main) {
            totalPendingPayable.value = cashStatsRepo.getTotalPendingPayable()
        }
    }

    private fun getPendingReceivable(){
        viewModelScope.launch(Dispatchers.Main) {
            totalPendingReceivable.value = cashStatsRepo.getTotalPendingReceivable()
        }
    }

    fun getMonthlyCashInflow(){
        viewModelScope.launch(Dispatchers.Main) {
            monthlyCashInflow.value = selectedMonthYearInflow.value?.let { cashStatsRepo.getMonthlyCashInflow(it) }
        }
    }

    fun getMonthlyCashOutflow(){
        viewModelScope.launch(Dispatchers.Main) {
            monthlyCashOutflow.value = selectedMonthYearOutflow.value?.let { cashStatsRepo.getMonthlyCashOutflow(it) }
        }
    }

}