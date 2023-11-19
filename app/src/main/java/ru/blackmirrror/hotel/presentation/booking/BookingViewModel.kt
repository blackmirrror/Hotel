package ru.blackmirrror.hotel.presentation.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.domain.models.Booking
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.usecases.GetBookingUseCase

class BookingViewModel(private val getBookingUseCase: GetBookingUseCase): ViewModel() {
    private val _booking = MutableLiveData<Booking?>()
    val booking: LiveData<Booking?> = _booking

    init {
        getHotel()
    }

    private fun getHotel() {
        viewModelScope.launch {
            val value = getBookingUseCase.execute()
            _booking.postValue(value)
        }
    }
}