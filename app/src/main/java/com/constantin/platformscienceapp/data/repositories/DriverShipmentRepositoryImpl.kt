package com.constantin.platformscienceapp.data.repositories

import com.constantin.platformscienceapp.data.source.database.dao.MappingDao
import com.constantin.platformscienceapp.data.source.database.entities.MappingEntity
import com.constantin.platformscienceapp.data.source.file.FileDataProvider
import com.constantin.platformscienceapp.domain.repository.DriverShipmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DriverShipmentRepositoryImpl(
    private val fileDataProvider: FileDataProvider,
    private val mappingDao: MappingDao
) : DriverShipmentRepository {
    override suspend fun getDrivers(): List<String>? =
        withContext(Dispatchers.IO) { fileDataProvider.getDrivers() }

    override suspend fun getShipments(): List<String>? =
        withContext(Dispatchers.IO) { fileDataProvider.getShipments() }

    override suspend fun getMappingByDriver(driverName: String): MappingEntity? =
        withContext(Dispatchers.IO) {
            mappingDao.getMappingByDriver(driverName)
        }

    override suspend fun insertMapping(mappingEntity: MappingEntity) {
        withContext(Dispatchers.IO) {
            mappingDao.insert(mappingEntity)
        }

    }
}
