package ru.blackmirrror.hotel.di

import org.koin.dsl.module
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase
import ru.blackmirrror.hotel.domain.usecases.GetRoomsUseCase

val domainModule = module {

    factory {
        GetHotelUseCase(repository = get())
    }

    factory {
        GetRoomsUseCase(repository = get())
    }
}