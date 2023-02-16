package com.example.jumpingminds.data.remote

import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.data.remote.dto.CharacterDto
import com.example.jumpingminds.data.remote.dto.HouseDto
import retrofit2.http.GET

interface IceFireAPI {

    @GET("/api/books")
    suspend fun getBooksList() : List<BookDto>

    @GET("/api/characters")
    suspend fun getCharactersList() : List<CharacterDto>

    @GET("/api/houses")
    suspend fun getHousesList() : List<HouseDto>
}