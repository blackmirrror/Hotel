package ru.blackmirrror.hotel.data.repositories

import ru.blackmirrror.hotel.data.api.NetworkState
import ru.blackmirrror.hotel.data.api.RemoteDataSource
import ru.blackmirrror.hotel.data.models.HotelResponse
import ru.blackmirrror.hotel.domain.models.Hotel
import ru.blackmirrror.hotel.domain.repositories.HotelRepository

class HotelRepositoryImpl(private val remoteDataSource: RemoteDataSource): HotelRepository {
    override suspend fun getHotel(): Hotel? {
        return when (val response = remoteDataSource.getHotel()) {
            is NetworkState.Success -> HotelResponse.dataToDomain(response.data)
            is NetworkState.Error -> null
        }
    }
}