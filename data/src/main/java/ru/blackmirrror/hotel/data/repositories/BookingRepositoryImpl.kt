package ru.blackmirrror.hotel.data.repositories

import ru.blackmirrror.hotel.data.api.NetworkState
import ru.blackmirrror.hotel.data.api.RemoteDataSource
import ru.blackmirrror.hotel.data.models.BookingResponse
import ru.blackmirrror.hotel.domain.models.Booking
import ru.blackmirrror.hotel.domain.repositories.BookingRepository

class BookingRepositoryImpl(private val remoteDataSource: RemoteDataSource): BookingRepository {
    override suspend fun getBooking(): Booking? {
        return when (val response = remoteDataSource.getBooking()) {
            is NetworkState.Success -> BookingResponse.dataToDomain(response.data)
            is NetworkState.Error -> null
        }
    }

}