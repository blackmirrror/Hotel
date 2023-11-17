package ru.blackmirrror.hotel.data.api

import ru.blackmirrror.hotel.data.models.HotelResponse

interface RemoteDataSource {

    suspend fun getHotel(): NetworkState<HotelResponse>
}