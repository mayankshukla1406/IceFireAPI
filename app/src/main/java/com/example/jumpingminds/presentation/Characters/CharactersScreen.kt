package com.example.jumpingminds.presentation.Characters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jumpingminds.Resource
import com.example.jumpingminds.domain.model.CharacterModel
import com.example.jumpingminds.presentation.ErrorScreen
import com.example.jumpingminds.presentation.LoadingScreen


@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel,
) {
    val character = characterViewModel.characters.collectAsState().value

    when (character) {
        is Resource.Success -> {
            character.data?.let {
                if (character.data.isEmpty()) {

                } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "CHARACTERS",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(character.data) { character ->
                                CharacterCard(character = character)
                            }
                        }
                    }
                }
            } ?: ErrorScreen("Data is Null") { characterViewModel.characters }
        }
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(character.message ?: "Error") { characterViewModel.characters }
        }
    }
}

