package com.vky342.openerp.data.ViewModels.Ledger

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Repositories.DayBookRepo
import com.vky342.openerp.data.Repositories.dayBookItem
import com.vky342.openerp.utility.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DayBookVM @Inject constructor( private val dayBookRepo: DayBookRepo): ViewModel(){

    val selectedDate : MutableLiveData<String> = MutableLiveData(getTodayDate())
    val dayBookList : MutableLiveData<List<dayBookItem>> = MutableLiveData(listOf<dayBookItem>())

    init {
        getDayBookList()
    }

    fun getDayBookList(){
        viewModelScope.launch(Dispatchers.Main) {
           dayBookList.value = selectedDate.value?.let { dayBookRepo.getDayLedger(it) }
        }
    }
}