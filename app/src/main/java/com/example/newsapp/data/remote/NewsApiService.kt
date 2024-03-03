package com.example.newsapp.data.remote

import com.example.newsapp.comman.Constants
import com.example.newsapp.data.model.dto.ApiDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("country")
        country: String = "in",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<ApiDto>

    @GET("v2/top-headlines")
    suspend fun getPagerTopHeadLines(
        @Query("country")
        country: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("category")
        category: String = "sports",
        @Query("pageSize")
        pageSize: Int = 6,
        @Query("apiKey")
    apiKey: String = Constants.API_KEY
    ): Response<ApiDto>

}