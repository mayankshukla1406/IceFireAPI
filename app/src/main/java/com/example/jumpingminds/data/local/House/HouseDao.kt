package com.example.jumpingminds.data.local.House

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouses(houses: List<HouseEntity>)

    @Query("SELECT * FROM house_entity")
    suspend fun getHouses(): List<HouseEntity>

    @Query("DELETE FROM house_entity")
    suspend fun deleteHouses()
}