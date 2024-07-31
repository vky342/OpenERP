package com.vky342.openerp.data.Entities.GeneralDataClasses


import java.time.LocalDate

data class ledgerItem(
    val date: LocalDate,
    val ledgerType : Int,
    val amount : Int,
    val transactionType : String,
    val ForeignId : Int
)

data class DayBookVoucher(
    val accountName : String,
    val voucher_type : String,
    val voucher_amount : Int
)