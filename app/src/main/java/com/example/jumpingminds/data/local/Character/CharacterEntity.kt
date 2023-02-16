package com.example.jumpingminds.data.local.Character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jumpingminds.domain.model.CharacterModel


@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val url: String,
    val aliases: List<String>,
    val books: List<String>,
    val born: String,
    val culture: String,
    val died: String,
    val father: String,
    val gender: String,
    val mother: String,
    val name: String,
    val playedBy: List<String>,
    val spouse: String,
    val titles: List<String>,
    val tvSeries: List<String>
){
    fun toCharacterModel() = CharacterModel(
        url=url,
        aliases = aliases,
        born = born,
        books = books,
        playedBy = playedBy,
        spouse = spouse,
        titles = titles,
        tvSeries = tvSeries,
        name = name,
        mother = mother,
        gender = gender,
        father = father,
        died = died,
        culture = culture
    )
}