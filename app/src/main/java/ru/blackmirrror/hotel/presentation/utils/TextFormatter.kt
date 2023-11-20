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

        fun numberToWord(number: Int): String {
            val units = arrayOf("Первый", "Второй", "Третий", "Четвёртый", "Пятый", "Шестой", "Седьмой", "Восьмой", "Девятый", "Десятый")
            if (number > 0 && number <= units.size) {
                return units[number - 1]
            } else {
                return "За пределами диапазона"
            }
        }
    }
}