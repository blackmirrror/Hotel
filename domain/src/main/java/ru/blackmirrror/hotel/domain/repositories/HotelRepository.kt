package ru.blackmirrror.hotel.domain.repositories

import ru.blackmirrror.hotel.domain.models.Hotel

interface HotelRepository {
    suspend fun getHotel(): Hotel?
}