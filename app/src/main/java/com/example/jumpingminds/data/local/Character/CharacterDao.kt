package com.example.jumpingminds.data.local.Character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun deleteCharacters()
}

