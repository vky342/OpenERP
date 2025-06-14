package com.vky342.openerp.data.ViewModels.Statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Repositories.CashStatsRepo
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

    init {
        getCashInflow()
        getCashOutflow()
        getPendingPayable()
        getPendingReceivable()
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

}