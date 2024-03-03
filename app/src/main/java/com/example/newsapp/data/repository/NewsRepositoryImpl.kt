package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.comman.Resource
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Articles
import com.example.newsapp.data.model.dto.ApiDto
import com.example.newsapp.data.model.dto.toArticles
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) :
    NewsRepository {


    override fun getTopHeadlines(): Flow<Resource<Articles?>> = flow {
        try {
            emit(Resource.Loading())
            val response = newsApiService.getTopHeadLines()
            if (response.isSuccessful) {
                val articles = response.body()?.toArticles()
                Log.d("mytag", "response is ${articles?.articles}")
                emit(Resource.Success(articles))
            } else {
                val error = response.message()
                emit(Resource.Error(error))
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }
    }

    override fun getPagerTopHealines(): Flow<Resource<Articles?>> = flow {
        try {
            emit(Resource.Loading())
            val response = newsApiService.getPagerTopHeadLines()
            if (response.isSuccessful) {
                val articles = response.body()?.toArticles()
                emit(Resource.Success(articles))
            } else {
                val error = response.message()
                emit(Resource.Error(error))
            }

        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }
    }

    override fun getAllBookMArkArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }

    override suspend fun saveArticle(article: Article) {
        newsDao.saveArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

}