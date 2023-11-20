package ru.blackmirrror.hotel.domain.models.local

data class Tourist (
    val id: Int,
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: String = "",
    var citizenship: String = "",
    var passportNumber: String = "",
    var passportTerm: String = ""
)