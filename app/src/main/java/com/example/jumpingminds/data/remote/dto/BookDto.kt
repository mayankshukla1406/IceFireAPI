package com.example.jumpingminds.data.remote.dto

import com.example.jumpingminds.data.local.Book.BookDao
import com.example.jumpingminds.data.local.Book.BookEntity

data class BookDto(
    val authors: List<String>,
    val characters: List<String>,
    val country: String,
    val isbn: String,
    val mediaType: String,
    val name: String,
    val numberOfPages: Int,
    val povCharacters: List<String>,
    val publisher: String,
    val released: String,
    val url: String,
) {
    fun toBookEntity() = BookEntity(
        isbn = isbn,
        authors = authors,
        characters = characters,
        country = country,
        mediaType = mediaType,
        name = name,
        numberOfPages = numberOfPages,
        povCharacters = povCharacters,
        publisher = publisher,
        released = released,
        url = url
    )
}