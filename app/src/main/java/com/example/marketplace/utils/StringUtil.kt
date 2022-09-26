package com.example.marketplace.utils

import java.text.NumberFormat
import java.util.*

class StringUtil {

    companion object {
        fun combineValueWithCurrency(value: Int, currencyCode: String): String {
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance(currencyCode)
            return format.format(value)
        }
    }
}