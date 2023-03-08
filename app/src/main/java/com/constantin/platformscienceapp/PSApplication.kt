package com.constantin.platformscienceapp

import android.app.Application
import com.constantin.platformscienceapp.data.di.databaseModule
import com.constantin.platformscienceapp.data.di.fileModule
import com.constantin.platformscienceapp.data.di.repositoryModule
import com.constantin.platformscienceapp.domain.di.usecaseModule
import com.constantin.platformscienceapp.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PSApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PSApplication)
            modules(
                fileModule + databaseModule +
                        repositoryModule + usecaseModule + viewModelModule
            )
        }
    }
}
