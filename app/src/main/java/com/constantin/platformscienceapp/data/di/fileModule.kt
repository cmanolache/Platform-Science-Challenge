package com.constantin.platformscienceapp.data.di

import com.constantin.platformscienceapp.data.source.file.FileDataProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val fileModule = module {
    single {
        FileDataProvider(androidApplication())
    }
}
