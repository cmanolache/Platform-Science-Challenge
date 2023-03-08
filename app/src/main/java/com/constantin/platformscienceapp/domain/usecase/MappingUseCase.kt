package com.constantin.platformscienceapp.domain.usecase

import com.constantin.platformscienceapp.data.source.database.entities.MappingEntity
import com.constantin.platformscienceapp.domain.repository.DriverShipmentRepository
import com.constantin.platformscienceapp.utils.count
import com.constantin.platformscienceapp.utils.hasCommonFactors

class MappingUseCase(
    private val repository: DriverShipmentRepository
) {

    suspend operator fun invoke() {
        var shipments = repository.getShipments() ?: emptyList()
        val drivers = repository.getDrivers() ?: emptyList()

        // iterate drivers
        for (driver in drivers) {
            var ssNumber = 0.0
            var optimizedShipment: String? = null
            val driverLength = driver.length

            // iterate shipments
            for (shipment in shipments) {
                val shipmentLength = shipment.length

                // check if destination length is even
                var baseSSNumber = if (shipmentLength % 2 == 0) {
                    // count number of vowels in driver's name
                    count(driver) * 1.5
                } else { // destination length is odd
                    // count number of consonants in driver's name
                    count(driver, false).toDouble()
                }

                // if common factors increase by 50%
                if (hasCommonFactors(shipmentLength, driverLength)) {
                    baseSSNumber += baseSSNumber / 2
                }

                // if new number is bigger, assign optimized shipment
                if (baseSSNumber > ssNumber) {
                    ssNumber = baseSSNumber
                    optimizedShipment = shipment
                }
            }

            // insert new mapping in DB if shipment not null
            optimizedShipment?.let {
                val newMapping = MappingEntity(
                    shipment = it,
                    driver = driver
                )
                repository.insertMapping(newMapping)
            }

            // remove assigned shipment from the iterated list
            shipments = shipments.filter {
                it != optimizedShipment
            } as MutableList<String>
        }
    }
}
