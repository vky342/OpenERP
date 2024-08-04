package com.vky342.openerp.data.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    override fun onCleared() {
        Log.d("VIEW model", "ended")
        super.onCleared()
    }

    fun Status_S(){
        Log.d("STATUS", "on ViewModel")
    }

    fun add_account_on_viewModel(){
        viewModelScope.launch {
            homeRepo.add_account()
        }
    }

}