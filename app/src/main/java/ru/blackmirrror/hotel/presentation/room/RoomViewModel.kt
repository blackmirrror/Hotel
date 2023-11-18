package ru.blackmirrror.hotel.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.domain.usecases.GetRoomsUseCase

class RoomViewModel(private val getRoomsUseCase: GetRoomsUseCase): ViewModel() {

    private val _rooms = MutableLiveData<List<Room>?>()
    val rooms: LiveData<List<Room>?> = _rooms

    init {
        getHotel()
    }

    private fun getHotel() {
        viewModelScope.launch {
            val list = getRoomsUseCase.execute()
            _rooms.postValue(list)
        }
    }
}