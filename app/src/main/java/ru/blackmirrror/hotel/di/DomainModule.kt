package ru.blackmirrror.hotel.di

import org.koin.dsl.module
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase

val domainModule = module {

    factory<GetHotelUseCase> {
        GetHotelUseCase(repository = get())
    }
}