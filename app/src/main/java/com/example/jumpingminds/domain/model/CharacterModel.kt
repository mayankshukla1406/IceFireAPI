package com.example.jumpingminds.domain.model

import com.example.jumpingminds.data.local.Character.CharacterEntity


data class CharacterModel(
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
    val tvSeries: List<String>,
    val url: String,
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
}
