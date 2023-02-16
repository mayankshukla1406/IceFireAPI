package com.example.jumpingminds.presentation.Houses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.presentation.ErrorScreen
import com.example.jumpingminds.presentation.LoadingScreen

@Composable
fun HousesScreen(
    houseViewModel: HouseViewModel,
) {

    when (val houses = houseViewModel.houses.collectAsState().value) {
        is Resource.Success -> {
            houses.data?.let {
                if (houses.data.isEmpty()) {

                } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "HOUSES",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        LazyColumn {
                            items(
                                items = houses.data,
                                itemContent = {
                                    HouseCard(house = it)
                                }
                            )
                        }
                    }
                }
            } ?: ErrorScreen("Data is Null") { houseViewModel.houses }
        }
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(houses.message ?: "Error") { houseViewModel.houses }
        }
    }

}