package ru.blackmirrror.hotel.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.domain.models.Hotel
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase

class HotelViewModel(private val getHotelUseCase: GetHotelUseCase) : ViewModel() {

    private val _hotel = MutableLiveData<Hotel?>()
    val hotel: LiveData<Hotel?> = _hotel

    init {
        getHotel()
    }

    private fun getHotel() {
        viewModelScope.launch {
            val chats = getHotelUseCase.execute()
            _hotel.postValue(chats)
        }
    }
}