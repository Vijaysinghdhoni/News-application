package com.example.newsapp.presentation.top_headlines

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Articles

data class PagerScreenState(
    val isLoading: Boolean = false,
    val articleList: List<Article>? = emptyList(),
    val error: String = "",
    val pageSize: Int = 0
)
