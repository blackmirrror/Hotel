package ru.blackmirrror.hotel.domain.models

data class Booking (
    var id             : Int?    = null,
    var hotelName      : String? = null,
    var hotelAdress    : String? = null,
    var horating       : Int?    = null,
    var ratingName     : String? = null,
    var departure      : String? = null,
    var arrivalCountry : String? = null,
    var tourDateStart  : String? = null,
    var tourDateStop   : String? = null,
    var numberOfNights : Int?    = null,
    var room           : String? = null,
    var nutrition      : String? = null,
    var tourPrice      : Int?    = null,
    var fuelCharge     : Int?    = null,
    var serviceCharge  : Int?    = null
)