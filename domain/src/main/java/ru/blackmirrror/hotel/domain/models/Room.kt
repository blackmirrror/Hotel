package ru.blackmirrror.hotel.domain.models

data class Room(
    var id            : Int?              = null,
    var name          : String?           = null,
    var price         : Int?              = null,
    var pricePer      : String?           = null,
    var peculiarities : ArrayList<String> = arrayListOf(),
    var imageUrls     : ArrayList<String> = arrayListOf()
)