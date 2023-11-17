package ru.blackmirrror.hotel

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.blackmirrror.hotel.di.appModule
import ru.blackmirrror.hotel.di.dataModule
import ru.blackmirrror.hotel.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)
        }
    }
}