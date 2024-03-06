package com.example.newsapp.presentation.top_headlinesScreen

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Articles

data class TopHeadLinesScreenState(
    val isLoading: Boolean = false,
    val articles: Articles? = null,
    val articleList: List<Article>? = emptyList(),
    val error: String = "",
    val topHeadlinesTxt: String = "Top Headlines"
)

//remove unwwanted state
