package ru.blackmirrror.hotel.domain.models

data class Hotel (
    var id            : Int?              = null,
    var name          : String?           = null,
    var adress        : String?           = null,
    var minimalPrice  : Int?              = null,
    var priceForIt    : String?           = null,
    var rating        : Int?              = null,
    var ratingName    : String?           = null,
    var imageUrls     : ArrayList<String> = arrayListOf(),
    var aboutTheHotel : AboutTheHotel?    = AboutTheHotel()
)