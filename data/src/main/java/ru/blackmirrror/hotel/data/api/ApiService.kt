package ru.blackmirrror.hotel.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.hotel.data.models.BookingResponse
import ru.blackmirrror.hotel.data.models.HotelResponse
import ru.blackmirrror.hotel.data.models.RoomsResponse

interface ApiService {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Response<HotelResponse>

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Response<RoomsResponse>

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooking(): Response<BookingResponse>
}