package ru.blackmirrror.hotel.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blackmirrror.hotel.presentation.hotel.HotelViewModel

val appModule = module {

    viewModel {
        HotelViewModel(getHotelUseCase = get())
    }
}