package ru.blackmirrror.hotel.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.hotel.data.models.HotelResponse

interface ApiService {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Response<HotelResponse>
}