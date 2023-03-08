package com.constantin.platformscienceapp.domain.usecase

import com.constantin.platformscienceapp.data.toShipment
import com.constantin.platformscienceapp.domain.models.Driver
import com.constantin.platformscienceapp.domain.models.Shipment
import com.constantin.platformscienceapp.domain.repository.DriverShipmentRepository

class GetShipmentUseCase(
    private val repository: DriverShipmentRepository
) {

    suspend operator fun invoke(driver: Driver): Shipment? {
        val mappingEntity = repository.getMappingByDriver(driver.name)
        return mappingEntity?.let {
            mappingEntity.shipment.toShipment()
        }
    }

}
