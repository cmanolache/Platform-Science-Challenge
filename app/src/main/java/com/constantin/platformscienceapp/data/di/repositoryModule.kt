package com.constantin.platformscienceapp.data.di

import com.constantin.platformscienceapp.data.repositories.DriverShipmentRepositoryImpl
import com.constantin.platformscienceapp.domain.repository.DriverShipmentRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::DriverShipmentRepositoryImpl) bind DriverShipmentRepository::class
}
