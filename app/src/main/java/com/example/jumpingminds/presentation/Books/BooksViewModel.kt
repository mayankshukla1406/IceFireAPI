package com.example.jumpingminds.presentation.Books

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.domain.use_cases.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val booksUseCase: BookUseCase
) : ViewModel() {
    private val _books = MutableStateFlow<Resource<List<BookDto>>>(Resource.Loading(true))
    val books: StateFlow<Resource<List<BookDto>>> get() = _books


    init {
        viewModelScope.launch {
            try {
                _books.value = Resource.Loading(true)
                val books = booksUseCase.getBooks()
                _books.value = Resource.Success(books.data)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error while fetching books", e)
                _books.value = Resource.Error("Error while fetching books", null)
            }
        }
    }
}
