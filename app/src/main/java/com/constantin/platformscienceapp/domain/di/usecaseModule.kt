package com.constantin.platformscienceapp.domain.di

import com.constantin.platformscienceapp.domain.usecase.GetDriverUseCase
import com.constantin.platformscienceapp.domain.usecase.GetShipmentUseCase
import com.constantin.platformscienceapp.domain.usecase.MappingUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory {
        GetShipmentUseCase(
            repository = get()
        )
    }

    factory {
        GetDriverUseCase(repository = get())
    }

    factory {
        MappingUseCase(
            repository = get(),
        )
    }
}
