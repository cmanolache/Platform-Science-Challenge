package com.constantin.platformscienceapp.domain.repository

import com.constantin.platformscienceapp.data.source.database.entities.MappingEntity

interface DriverShipmentRepository {
    suspend fun getDrivers(): List<String>?
    suspend fun getShipments(): List<String>?
    suspend fun getMappingByDriver(driverName: String): MappingEntity?
    suspend fun insertMapping(mappingEntity: MappingEntity)
}
