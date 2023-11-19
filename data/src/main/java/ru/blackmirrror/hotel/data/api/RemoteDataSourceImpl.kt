package ru.blackmirrror.hotel.data.api

import retrofit2.Response
import ru.blackmirrror.hotel.data.models.BookingResponse
import ru.blackmirrror.hotel.data.models.HotelResponse
import ru.blackmirrror.hotel.data.models.RoomsResponse

class RemoteDataSourceImpl(private val service: ApiService): RemoteDataSource {
    override suspend fun getHotel(): NetworkState<HotelResponse> {
        return getNetworkState(service.getHotel())
    }

    override suspend fun getRooms(): NetworkState<RoomsResponse> {
        return getNetworkState(service.getRooms())
    }

    override suspend fun getBooking(): NetworkState<BookingResponse> {
        return getNetworkState(service.getBooking())
    }

    private inline fun <reified T> getNetworkState(response: Response<T>): NetworkState<T> {
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}