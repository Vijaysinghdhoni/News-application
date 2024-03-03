package com.example.newsapp.data.model.dto

import com.example.newsapp.data.model.Articles
import com.google.gson.annotations.SerializedName

data class ApiDto(
    @SerializedName("articles")
    val articleDtos: List<ArticleDto>,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)



fun ApiDto.toArticles(): Articles {
    return Articles(
        articles = articleDtos.map { it.toArticle() },
        status = status!!,
        totalResults = totalResults!!
    )
}