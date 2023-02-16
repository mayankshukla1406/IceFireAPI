package com.example.jumpingminds.presentation.BottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.presentation.Books.BookViewModel
import com.example.jumpingminds.presentation.Books.BooksScreen
import com.example.jumpingminds.presentation.Characters.CharacterScreen
import com.example.jumpingminds.presentation.Characters.CharacterViewModel
import com.example.jumpingminds.presentation.Houses.HouseViewModel
import com.example.jumpingminds.presentation.Houses.HousesScreen

@Composable
fun MainNavigation(
    navHostController: NavHostController,
    bookViewModel: BookViewModel,
    houseViewModel: HouseViewModel,
    characterViewModel: CharacterViewModel
) {

    NavHost(navController = navHostController, startDestination = BottomBarRoutes.Books.route) {
        composable(BottomBarRoutes.Books.route) {
            BooksScreen(bookViewModel = bookViewModel)
        }
        composable(BottomBarRoutes.Characters.route) {
            CharacterScreen(characterViewModel = characterViewModel)
        }
        composable(BottomBarRoutes.Houses.route) {
            HousesScreen(houseViewModel = houseViewModel)
        }
    }

}
