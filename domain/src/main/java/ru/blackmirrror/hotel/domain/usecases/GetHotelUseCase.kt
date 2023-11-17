package ru.blackmirrror.hotel.domain.usecases

import ru.blackmirrror.hotel.domain.models.Hotel
import ru.blackmirrror.hotel.domain.repositories.HotelRepository

class GetHotelUseCase(private val repository: HotelRepository) {
    suspend fun execute(): Hotel? {
        return repository.getHotel()
    }
}