package com.example.newsapp.presentation.bookmark

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Articles

data class BookMarkScreenState(
    val articles : List<Article> = emptyList(),

)
