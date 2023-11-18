package ru.blackmirrror.hotel.data.repositories

import ru.blackmirrror.hotel.data.api.NetworkState
import ru.blackmirrror.hotel.data.api.RemoteDataSource
import ru.blackmirrror.hotel.data.models.RoomsResponse
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.repositories.RoomRepository

class RoomRepositoryImpl(private val remoteDataSource: RemoteDataSource): RoomRepository {
    override suspend fun getRooms(): List<Room>? {
        return when (val response = remoteDataSource.getRooms()) {
            is NetworkState.Success -> RoomsResponse.dataToDomain(response.data)
            is NetworkState.Error -> null
        }
    }
}