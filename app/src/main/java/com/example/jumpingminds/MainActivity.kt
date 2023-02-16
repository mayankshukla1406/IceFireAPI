package com.example.jumpingminds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.jumpingminds.presentation.Books.BookViewModel
import com.example.jumpingminds.presentation.Books.BooksScreen
import com.example.jumpingminds.presentation.BottomBar.BottomBar
import com.example.jumpingminds.presentation.BottomBar.MainNavigation
import com.example.jumpingminds.presentation.Characters.CharacterViewModel
import com.example.jumpingminds.presentation.Houses.HouseViewModel
import com.example.jumpingminds.ui.theme.JumpingMindsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JumpingMindsTheme {
                // A surface container using the 'background' color from the theme
                var showSplashScreen by remember { mutableStateOf(true) }

                if (showSplashScreen) {
                    SplashScreen {
                        showSplashScreen = false
                    }
                } else {
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                        val bookViewModel: BookViewModel = hiltViewModel()
                        val houseViewModel: HouseViewModel = hiltViewModel()
                        val characterViewModel: CharacterViewModel = hiltViewModel()
                        val navController = rememberNavController()
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text("JUMPING MINDS APP") },
                                )
                            },
                            bottomBar = {
                                BottomBar(navController = navController)
                            }
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(bottom = 20.dp, top = 15.dp)) {
                                MainNavigation(
                                    navHostController = navController,
                                    bookViewModel = bookViewModel,
                                    houseViewModel = houseViewModel,
                                    characterViewModel = characterViewModel,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = Color.Unspecified
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
}