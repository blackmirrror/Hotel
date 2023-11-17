package ru.blackmirrror.hotel.data.api

import retrofit2.Response
import ru.blackmirrror.hotel.data.models.HotelResponse

class RemoteDataSourceImpl(private val service: ApiService): RemoteDataSource {
    override suspend fun getHotel(): NetworkState<HotelResponse> {
        return getNetworkState(service.getHotel())
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