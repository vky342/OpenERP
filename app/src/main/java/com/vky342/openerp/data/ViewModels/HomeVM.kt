package com.vky342.openerp.data.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vky342.openerp.data.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    val todaySaleData : MutableLiveData<Double> = MutableLiveData(0.0)
    val todayReceiptData : MutableLiveData<Double> = MutableLiveData(0.0)

    init {
        setData()
    }

    fun setData(){
        viewModelScope.launch(Dispatchers.Main) {
            todaySaleData.value = homeRepo.getTodaySale()
            todayReceiptData.value = homeRepo.getTodayReceipt()
        }
    }

}