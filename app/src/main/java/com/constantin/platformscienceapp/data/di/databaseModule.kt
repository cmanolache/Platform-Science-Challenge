package com.constantin.platformscienceapp.data.di

import androidx.room.Room
import com.constantin.platformscienceapp.data.source.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java, "ps-database"
        ).build()
    }

    single {
        get<AppDatabase>().mappingDao()
    }
}
