package ru.blackmirrror.hotel.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase
import ru.blackmirrror.hotel.domain.usecases.GetRoomsUseCase
import ru.blackmirrror.hotel.presentation.hotel.HotelViewModel

class RoomViewModel(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {

    private val _rooms = MutableLiveData<List<Room>?>()
    val rooms: LiveData<List<Room>?> = _rooms

    private val _hotelName = MutableLiveData<String?>()
    val hotelName: LiveData<String?> = _hotelName


    fun getRooms(hotelId: Int) {
        viewModelScope.launch {
            val list = getRoomsUseCase.execute(hotelId)
            _rooms.postValue(list)
        }
    }

    fun getHotelName(hotelId: Int) {
        viewModelScope.launch {
            val value = getHotelUseCase.execute(hotelId)?.name
            _hotelName.postValue(value)
        }
    }
}