package com.example.jumpingminds.data.local.House

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jumpingminds.domain.model.HouseModel

@Entity(tableName = "house_entity")
data class HouseEntity(
    @PrimaryKey
    val name: String,
    val ancestralWeapons: List<String>,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val titles: List<String>,
    val url: String,
    val words: String
) {
    fun toHouseModel() = HouseModel(
        ancestralWeapons = ancestralWeapons,
        coatOfArms = coatOfArms,
        currentLord = currentLord,
        diedOut = diedOut,
        founded = founded,
        founder = founder,
        heir = heir,
        name = name,
        overlord = overlord,
        region = region,
        seats = seats,
        titles = titles,
        url = url,
        words = words
    )
}