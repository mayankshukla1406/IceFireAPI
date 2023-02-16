package com.example.jumpingminds.domain.repository

import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.domain.model.HouseModel

interface IceFireRepository {
    suspend fun getBooks(): Resource<List<BookDto>>
    suspend fun getCharacters(): Resource<List<CharacterModel>>
    suspend fun getHouses(): Resource<List<HouseModel>>
}