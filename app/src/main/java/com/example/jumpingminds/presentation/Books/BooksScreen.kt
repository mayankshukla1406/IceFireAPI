package com.example.jumpingminds.presentation.Books

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.presentation.Characters.CharacterViewModel
import com.example.jumpingminds.presentation.ErrorScreen
import com.example.jumpingminds.presentation.Houses.HouseViewModel
import com.example.jumpingminds.presentation.LoadingScreen

@Composable
fun BooksScreen(
    bookViewModel: BookViewModel,

) {

    when (val books = bookViewModel.books.collectAsState().value) {
        is Resource.Success -> {
            books.data?.let {
                if (books.data.isEmpty()) {

                } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "BOOKS",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        LazyColumn {
                            items(
                                items = books.data,
                                itemContent = {
                                    BookCard(book = it)
                                }
                            )
                        }
                    }
                }
            } ?: ErrorScreen("Data is Null") { bookViewModel.books }
        }
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(books.message ?: "Error") { bookViewModel.books }
        }
    }
}

