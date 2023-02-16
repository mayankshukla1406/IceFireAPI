package com.example.jumpingminds.data.repository

import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.remote.IceFireAPI
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.data.remote.dto.HouseDto
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.domain.model.HouseModel
import com.example.jumpingminds.domain.repository.IceFireRepository
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class IceFireRepositoryImpl @Inject constructor(
    private val iceFireAPI: IceFireAPI
):IceFireRepository {
    override suspend fun getBooks(): Resource<List<BookDto>> {
        return try {
            val books = iceFireAPI.getBooksList()
            if (books.isNullOrEmpty()) {
                Resource.Error("No books found")
            } else {
                Resource.Success(books)
            }
        } catch (e: HttpException) {
            Resource.Error("Error: ${e.code()}")
        } catch (e: ConnectException) {
            Resource.Error("Error: Connection error")
        } catch (e: SocketTimeoutException) {
            Resource.Error("Error: Connection timeout")
        } catch (e: UnknownHostException) {
            Resource.Error("Error: Unknown host")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getCharacters(): Resource<List<CharacterModel>> {
        return try {
            val characters = iceFireAPI.getCharactersList().map { it.toCharacterModel() }
            if (characters.isNullOrEmpty()) {
                Resource.Error("No characters found")
            } else {
                Resource.Success(characters)
            }
        } catch (e: HttpException) {
            Resource.Error("Error: ${e.code()}")
        } catch (e: ConnectException) {
            Resource.Error("Error: Connection error")
        } catch (e: SocketTimeoutException) {
            Resource.Error("Error: Connection timeout")
        } catch (e: UnknownHostException) {
            Resource.Error("Error: Unknown host")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getHouses(): Resource<List<HouseModel>> {
        return try {
            val houses = iceFireAPI.getHousesList().map { it.toHouseModel() }
            if (houses.isNullOrEmpty()) {
                Resource.Error("No houses found")
            } else {
                Resource.Success(houses)
            }
        } catch (e: HttpException) {
            Resource.Error("Error: ${e.code()}")
        } catch (e: ConnectException) {
            Resource.Error("Error: Connection error")
        } catch (e: SocketTimeoutException) {
            Resource.Error("Error: Connection timeout")
        } catch (e: UnknownHostException) {
            Resource.Error("Error: Unknown host")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}