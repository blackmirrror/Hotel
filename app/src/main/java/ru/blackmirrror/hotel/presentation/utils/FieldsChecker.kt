package ru.blackmirrror.hotel.presentation.utils

class FieldsChecker {
    companion object {
        fun checkEmail(email: String): Boolean {
            val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
            return email.matches(emailRegex.toRegex())
        }
    }
}