package com.example.jumpingminds.data.local.Character

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jumpingminds.data.local.StringListTypeConverter

@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class IceFireCharacterDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}