package com.example.jumpingminds.presentation.Houses

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.domain.model.HouseModel
import com.example.jumpingminds.domain.use_cases.BookUseCase
import com.example.jumpingminds.domain.use_cases.HouseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HouseViewModel @Inject constructor(
    private val houseUseCase: HouseUseCase
) : ViewModel() {
    private val _houses = MutableStateFlow<Resource<List<HouseModel>>>(Resource.Loading(true))
    val houses: StateFlow<Resource<List<HouseModel>>> get() = _houses


    init {
        viewModelScope.launch {
            try {
                _houses.value = Resource.Loading(true)
                val books = houseUseCase.getHouses()
                _houses.value = Resource.Success(books.data)
            } catch (e: Exception) {
                _houses.value = Resource.Error("Error while fetching houses", null)
            }
        }
    }
}