package ru.blackmirrror.hotel.presentation.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class TextFormatter {
    companion object {
        fun formatPrice(amount: Int): String {
            val formatter = DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale.getDefault()))
            var result = "${formatter.format(amount)} ₽"
            result = result.replace(",", " ")
            return result
        }
    }
}