package ru.blackmirrror.hotel.domain.usecases

import ru.blackmirrror.hotel.domain.models.Booking
import ru.blackmirrror.hotel.domain.repositories.BookingRepository

class GetBookingUseCase(private val repository: BookingRepository) {
    suspend fun execute(): Booking? {
        return repository.getBooking()
    }
}