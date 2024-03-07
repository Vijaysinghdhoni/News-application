package com.example.newsapp.presentation.bookmarkScreen

import com.example.newsapp.data.model.Article

data class BookMarkScreenState(
    val articles : List<Article> = emptyList(),
    val isDelete: Boolean = false,
    val toast:String = ""
)
