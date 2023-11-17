package ru.blackmirrror.hotel.di

import org.koin.dsl.module
import ru.blackmirrror.hotel.data.api.ApiFactory
import ru.blackmirrror.hotel.data.api.ApiService
import ru.blackmirrror.hotel.data.api.RemoteDataSource
import ru.blackmirrror.hotel.data.api.RemoteDataSourceImpl
import ru.blackmirrror.hotel.data.repositories.HotelRepositoryImpl
import ru.blackmirrror.hotel.domain.repositories.HotelRepository

val dataModule = module {

    single<HotelRepository> {
        HotelRepositoryImpl(remoteDataSource = get())
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(service = get())
    }

    single<ApiService> {
        ApiFactory.create()
    }
}