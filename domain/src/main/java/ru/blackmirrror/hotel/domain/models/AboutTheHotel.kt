package ru.blackmirrror.hotel.domain.models

data class AboutTheHotel (
    var description   : String?           = null,
    var peculiarities : ArrayList<String> = arrayListOf()
)