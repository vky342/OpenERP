package com.vky342.openerp.data.ViewModels

import androidx.lifecycle.ViewModel
import com.vky342.openerp.data.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {


}