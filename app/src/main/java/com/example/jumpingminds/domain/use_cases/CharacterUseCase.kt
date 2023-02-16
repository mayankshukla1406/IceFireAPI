package com.example.jumpingminds.domain.use_cases

import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.local.Character.CharacterDao
import com.example.jumpingminds.data.local.Character.CharacterEntity
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.domain.repository.IceFireRepository
import javax.inject.Inject


class CharacterUseCase @Inject constructor(
    private val iceFireRepository: IceFireRepository,
    private val characterDao: CharacterDao
) {
    private var cachedCharacters: List<CharacterEntity>? = null

    suspend fun getCharacters(): Resource<List<CharacterModel>> {
        return if (checkCache()) {
            var filteredCharacters = cachedCharacters!!.map { it.toCharacterModel() }

            Resource.Success(filteredCharacters)
        } else {
            val resource = iceFireRepository.getCharacters()
            if (resource is Resource.Success) {
                resource.data?.let {
                    cachedCharacters = resource.data.map {
                        it.toCharacterEntity()
                    }
                    cachedCharacters?.let { characterEntities ->
                        characterDao.insertCharacters(characterEntities)
                    }
                }?:Resource.Error<List<CharacterModel>>("Null Data Received from API")
            }
            resource
        }
    }

    private suspend fun checkCache() : Boolean {
        cachedCharacters = characterDao.getCharacters()
        return cachedCharacters?.let {
            return it.isNotEmpty()
        }?:false
    }

    suspend fun invalidateCache() {
        cachedCharacters = null
    }
}