package ru.blackmirrror.hotel.domain.repositories

import ru.blackmirrror.hotel.domain.models.Booking

interface BookingRepository {
    suspend fun getBooking(): Booking?
}