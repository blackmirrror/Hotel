package ru.blackmirrror.hotel.data.api

import ru.blackmirrror.hotel.data.models.HotelResponse
import ru.blackmirrror.hotel.data.models.RoomsResponse

interface RemoteDataSource {
    suspend fun getHotel(): NetworkState<HotelResponse>
    suspend fun getRooms(): NetworkState<RoomsResponse>
}