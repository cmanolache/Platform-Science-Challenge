package com.constantin.platformscienceapp.data.source.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mapping")
data class MappingEntity(
    @PrimaryKey
    val shipment: String,
    val driver: String
)
