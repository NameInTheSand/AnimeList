package com.ukrdroiddev.myanimelist

import android.app.Application
import com.ukrdroiddev.data.koinModules.dataModule
import com.ukrdroiddev.presentation.koinModules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AnimeListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AnimeListApp)
            modules(
                dataModule,
                presentationModule
            )
        }
    }
}