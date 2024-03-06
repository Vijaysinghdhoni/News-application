package com.example.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home

data class NavigationState(
    val navItem: List<BottomNavItem> = listOf(
        BottomNavItem(
            title = Screen.TopHeadlinesScreen.route,
            selectedIcon = Icons.Filled.Home,
            unSelectedItem = Icons.Outlined.Home
        ),
        BottomNavItem(
            title = "Bookmark",
            selectedIcon = Icons.Filled.Favorite,
            unSelectedItem = Icons.Outlined.FavoriteBorder
        )
    ),
    val selectedItem: Int = 0
)
