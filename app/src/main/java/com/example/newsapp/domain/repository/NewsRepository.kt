package com.example.newsapp.domain.repository

import com.example.newsapp.comman.Resource
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Articles
import com.example.newsapp.data.model.dto.ApiDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(): Flow<Resource<Articles?>>

    fun getPagerTopHealines(): Flow<Resource<Articles?>>

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getAllBookMArkArticles(): Flow<List<Article>>

}