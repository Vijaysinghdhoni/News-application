package com.example.newsapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unSelectedItem : ImageVector
)
