package com.example.newsapp.presentation.top_headlinesScreen

import com.example.newsapp.data.model.Article

data class PagerScreenState(
    val isLoading: Boolean = false,
    val articleList: List<Article>? = emptyList(),
    val error: String = "",
    val pageSize: Int = 0
)
