package com.vky342.openerp.data.ViewModels.Statistics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.SaleStatsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SaleStatsVM @Inject constructor( private val saleStatsRepo: SaleStatsRepo): ViewModel(){

    val totalTodaySales : MutableLiveData<Double> = MutableLiveData<Double>(0.0)
    val listOfSaleEntries : MutableLiveData<List<SaleEntry>> = MutableLiveData(listOf())
    val totalMonthlySale : MutableLiveData<Double> = MutableLiveData(0.0)

    fun getSalesByDate(date: String? = null) { // Date should be format dd/mm/yyyy // Default is today
        viewModelScope.launch(Dispatchers.Main) {
           totalTodaySales.value = saleStatsRepo.getSalesTotalByDate(date)
        }
    }

    fun getSalesByProduct(itemName : String){
        viewModelScope.launch(Dispatchers.Main) {
           listOfSaleEntries.value = saleStatsRepo.getSalesByProduct(itemName)
        }
    }

    fun getMonthlySalesTotal(month : String? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            totalMonthlySale.value = saleStatsRepo.getMonthlySalesTotal(month)
        }
    }
}