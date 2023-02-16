package com.example.jumpingminds.data.local.Book

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("SELECT * FROM books")
    suspend fun getBooks(): List<BookEntity>?

    @Query("DELETE FROM books")
    suspend fun deleteBooks()
}

