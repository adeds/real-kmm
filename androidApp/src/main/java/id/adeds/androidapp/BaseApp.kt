package id.adeds.androidapp

import android.app.Application
import id.adeds.androidapp.di.Injector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            androidLogger()
            modules(
                Injector.baseModule,
                Injector.clientModule,
                Injector.repositoryModule,
                Injector.useCaseModule,
                Injector.viewModelModule,
            )
        }
    }
}