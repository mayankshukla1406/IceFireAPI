package com.example.jumpingminds.data.local.Book

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jumpingminds.data.local.StringListTypeConverter


@Database(entities = [BookEntity::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class IceFireBookDatabase : RoomDatabase() {
    abstract fun booksDao(): BookDao
}