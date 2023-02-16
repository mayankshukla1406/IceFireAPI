package com.example.jumpingminds.presentation.Characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.domain.use_cases.BookUseCase
import com.example.jumpingminds.domain.use_cases.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _characters = MutableStateFlow<Resource<List<CharacterModel>>>(Resource.Loading(true))
    val characters: StateFlow<Resource<List<CharacterModel>>> get() = _characters

    init {
        viewModelScope.launch {
            try {
                _characters.value = Resource.Loading(true)
                val characters = characterUseCase.getCharacters()
                _characters.value = Resource.Success(characters.data)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error while fetching characters", e)
                _characters.value = Resource.Error("Error while fetching characters", null)
            }
        }
    }
}