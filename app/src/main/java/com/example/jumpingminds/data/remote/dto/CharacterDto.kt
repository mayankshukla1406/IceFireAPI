package com.example.jumpingminds.data.remote.dto

import com.example.jumpingminds.data.local.Character.CharacterEntity
import com.example.jumpingminds.domain.model.CharacterModel

data class CharacterDto(
    val aliases: List<String>,
    val allegiances: List<Any>,
    val books: List<String>,
    val born: String,
    val culture: String,
    val died: String,
    val father: String,
    val gender: String,
    val mother: String,
    val name: String,
    val playedBy: List<String>,
    val povBooks: List<Any>,
    val spouse: String,
    val titles: List<String>,
    val tvSeries: List<String>,
    val url: String
) {
    fun toCharacterEntity() = CharacterEntity(
        aliases = aliases,
        books = books,
        born = born,
        culture = culture,
        died = died,
        father = father,
        gender = gender,
        mother = mother,
        name = name,
        playedBy = playedBy,
        spouse = spouse,
        titles = titles,
        tvSeries = tvSeries,
        url = url
    )
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