package com.vky342.openerp.utility

import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getTodayDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date())
}

fun calculateEffectiveBalancesWithLogic(
    ledgerList: List<AccountLedgerItem>
): List<AccountLedgerItem> {
    var localBalance = 0.0

    return ledgerList.map { item ->
        val updatedItem = when {
            item.BillType == "Receipt" -> {
                localBalance += item.BillAmount
                item.copy(effectiveBalance = localBalance)
            }
            item.BillType == "Payment" -> {
                localBalance -= item.BillAmount
                item.copy(effectiveBalance = localBalance)
            }
            item.BillType == "Purchase" && item.CashOrCredit == "Credit" -> {
                localBalance += item.BillAmount
                item.copy(effectiveBalance = localBalance)
            }
            item.BillType == "Sale" && item.CashOrCredit == "Credit" -> {
                localBalance -= item.BillAmount
                item.copy(effectiveBalance = localBalance)
            }
            item.BillType == "Purchase" && item.CashOrCredit == "Cash" -> {
                item.copy(effectiveBalance = localBalance) // No change
            }
            item.BillType == "Sale" && item.CashOrCredit == "Cash" -> {
                item.copy(effectiveBalance = localBalance) // No change
            }
            else -> item.copy(effectiveBalance = localBalance) // Default case
        }
        updatedItem
    }
}
