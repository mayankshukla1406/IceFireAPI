package com.example.jumpingminds.data.remote.dto

import com.example.jumpingminds.data.local.House.HouseEntity
import com.example.jumpingminds.domain.model.HouseModel

data class HouseDto(
    val ancestralWeapons: List<String>,
    val cadetBranches: List<Any>,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val name: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val swornMembers: List<Any>,
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
    fun toHouseEntity() = HouseEntity(
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