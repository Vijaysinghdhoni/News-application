package com.example.newsapp.presentation

sealed class Screen(val route: String) {

    object TopHeadlinesScreen : Screen("Home")
    object BookMarkScreen : Screen("BookMark")
    object NewsDetailScreen : Screen("NewsDetailScreen")
}