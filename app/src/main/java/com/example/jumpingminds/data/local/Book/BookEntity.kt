package com.example.jumpingminds.data.local.Book

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jumpingminds.data.remote.dto.BookDto

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val isbn: String,
    val authors: List<String>,
    val characters: List<String>,
    val country: String,
    val mediaType: String,
    val name: String,
    val numberOfPages: Int,
    val povCharacters: List<String>,
    val publisher: String,
    val released: String,
    val url: String,
) {
    fun toBookDto() = BookDto(
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