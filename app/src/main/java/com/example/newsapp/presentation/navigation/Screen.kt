package com.example.newsapp.presentation.navigation

sealed class Screen(val route: String) {

    object TopHeadlinesScreen : Screen("Home")
    object BookMarkScreen : Screen("BookMark")
    object NewsDetailScreen : Screen("NewsDetailScreen")
}