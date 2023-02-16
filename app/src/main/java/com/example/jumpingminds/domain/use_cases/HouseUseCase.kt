package com.example.jumpingminds.domain.use_cases

import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.local.House.HouseDao
import com.example.jumpingminds.data.local.House.HouseEntity
import com.example.jumpingminds.data.remote.dto.HouseDto
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.domain.model.HouseModel
import com.example.jumpingminds.domain.repository.IceFireRepository


class HouseUseCase(
    private val iceFireRepository: IceFireRepository,
    private val houseDao: HouseDao,
) {
    private var cachedHouses: List<HouseEntity>? = null

    suspend fun getHouses(): Resource<List<HouseModel>> {
        return if (checkCache()) {
            var filteredHouses = cachedHouses!!.map { it.toHouseModel() }
            Resource.Success(filteredHouses)
        } else {
            val resource = iceFireRepository.getHouses()
            if (resource is Resource.Success) {
                resource.data?.let {
                    cachedHouses = resource.data.map {
                        it.toHouseEntity()
                    }
                    cachedHouses?.let { houseEntities ->
                        houseDao.insertHouses(houseEntities)
                    }
                }?:Resource.Error<List<CharacterModel>>("Null Data Received from API")
            }
            resource
        }
    }

    private suspend fun checkCache() : Boolean {
        cachedHouses = houseDao.getHouses()
        return cachedHouses?.let {
            return it.isNotEmpty()
        }?:false
    }
    suspend fun invalidateCache() {
        cachedHouses = null
    }
}