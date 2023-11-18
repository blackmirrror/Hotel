package ru.blackmirrror.hotel.domain.repositories

import ru.blackmirrror.hotel.domain.models.Room

interface RoomRepository {
    suspend fun getRooms(): List<Room>?
}