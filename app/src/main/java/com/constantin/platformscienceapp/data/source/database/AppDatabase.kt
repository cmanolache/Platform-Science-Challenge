package com.constantin.platformscienceapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.constantin.platformscienceapp.data.source.database.dao.MappingDao
import com.constantin.platformscienceapp.data.source.database.entities.MappingEntity

@Database(entities = [MappingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mappingDao(): MappingDao
}
