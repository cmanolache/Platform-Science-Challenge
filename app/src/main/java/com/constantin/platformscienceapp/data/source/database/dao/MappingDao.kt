package com.constantin.platformscienceapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.constantin.platformscienceapp.data.source.database.entities.MappingEntity

@Dao
interface MappingDao {
    @Query("SELECT * FROM mapping WHERE driver= :driver LIMIT 1")
    suspend fun getMappingByDriver(driver: String): MappingEntity?

    @Query("SELECT * FROM mapping WHERE shipment = :shipment LIMIT 1")
    suspend fun getMappingByShipment(shipment: String): MappingEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mappingEntity: MappingEntity)
}
