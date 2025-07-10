package com.vky342.openerp.utility

import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getTodayDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date())
}

fun getCurrentMonthYear(): String {
    val formatter = SimpleDateFormat("MM/yyyy", Locale.getDefault())
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

fun parseStrictDouble(input: String): Double {
    val trimmed = input.trim()

    if (trimmed.isEmpty()) {
        throw IllegalArgumentException("Input is empty or whitespace only.")
    }

    // Optional: Normalize input by replacing comma with dot if needed
    // But this code assumes strict "." decimal format, not European style commas
    if (trimmed.contains(',')) {
        throw IllegalArgumentException("Commas are not allowed in numeric input.")
    }

    // Regular expression to match a strict decimal number (optional sign, integer or decimal)
    val numberRegex = Regex("^[-+]?\\d+(\\.\\d+)?\$")

    if (!numberRegex.matches(trimmed)) {
        throw IllegalArgumentException("Invalid numeric format: '$input'")
    }

    return try {
        trimmed.toDouble()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Failed to parse double from input: '$input'", e)
    }
}

