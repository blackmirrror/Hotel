package ru.blackmirrror.hotel.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.domain.models.Hotel
import ru.blackmirrror.hotel.domain.models.local.Feature
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase

class HotelViewModel(private val getHotelUseCase: GetHotelUseCase) : ViewModel() {

    private val _hotel = MutableLiveData<Hotel?>()
    val hotel: LiveData<Hotel?> = _hotel

    var features: List<Feature> = listOf()

    init {
        initFeatures()
        getHotel()
    }

    private fun getHotel() {
        viewModelScope.launch {
            val value = getHotelUseCase.execute()
            _hotel.postValue(value)
        }
    }

    private fun initFeatures() {
        features = listOf(
            Feature(1, R.drawable.ic_emoji_happy_square, "Удобства", "Самое необходимое"),
            Feature(2, R.drawable.ic_tick_square, "Что включено", "Самое необходимое"),
            Feature(3, R.drawable.ic_close_square, "Что не включено", "Самое необходимое"),
        )
    }
}