package ru.blackmirrror.hotel.presentation.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.domain.models.Booking
import ru.blackmirrror.hotel.domain.models.Hotel
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.usecases.GetBookingUseCase
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase
import ru.blackmirrror.hotel.presentation.hotel.HotelViewModel

class BookingViewModel(
    private val getBookingUseCase: GetBookingUseCase,
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {

    private val _booking = MutableLiveData<Booking?>()
    val booking: LiveData<Booking?> = _booking

    private val _hotel = MutableLiveData<Hotel?>()
    val hotel: LiveData<Hotel?> = _hotel

    fun getBooking(roomId: Int) {
        viewModelScope.launch {
            val value = getBookingUseCase.execute(roomId)
            _booking.postValue(value)
        }
    }

    fun getHotel(hotelId: Int) {
        viewModelScope.launch {
            val value = getHotelUseCase.execute(hotelId)
            _hotel.postValue(value)
        }
    }
}