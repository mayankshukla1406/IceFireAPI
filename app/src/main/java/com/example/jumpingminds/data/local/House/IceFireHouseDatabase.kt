package com.example.jumpingminds.data.local.House

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jumpingminds.data.local.StringListTypeConverter

@Database(entities = [HouseEntity::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class IceFireHouseDatabase : RoomDatabase() {
    abstract fun housesDao(): HouseDao
}