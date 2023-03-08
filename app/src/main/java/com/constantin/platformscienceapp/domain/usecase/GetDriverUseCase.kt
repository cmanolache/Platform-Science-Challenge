package com.constantin.platformscienceapp.domain.usecase

import com.constantin.platformscienceapp.data.toDriver
import com.constantin.platformscienceapp.domain.models.Driver
import com.constantin.platformscienceapp.domain.repository.DriverShipmentRepository

class GetDriverUseCase (
    private val repository: DriverShipmentRepository
) {
    suspend operator fun invoke(): List<Driver>? {
        val drivers = repository.getDrivers()
        return drivers?.map {
            it.toDriver()
        }
    }
}

