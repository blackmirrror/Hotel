package ru.blackmirrror.hotel.domain.usecases

import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.repositories.RoomRepository

class GetRoomsUseCase(private val repository: RoomRepository) {
    suspend fun execute(hotelId: Int): List<Room>? {
        return repository.getRooms()
    }
}