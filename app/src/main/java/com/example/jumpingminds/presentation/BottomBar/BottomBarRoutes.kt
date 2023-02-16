package com.example.jumpingminds.presentation.BottomBar

import com.example.jumpingminds.R

sealed class BottomBarRoutes(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    object Characters : BottomBarRoutes(
        route = "character",
        title = "Characters",
        icon = R.drawable.asd,
        icon_focused = R.drawable.asd
    )

    object Houses : BottomBarRoutes(
        route = "houses",
        title = "Houses",
        icon = R.drawable.houses,
        icon_focused = R.drawable.houses
    )

    object Books : BottomBarRoutes(
        route = "books",
        title = "Books",
        icon = R.drawable.books,
        icon_focused = R.drawable.books
    )
}