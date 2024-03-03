package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.comman.Constants
import com.example.newsapp.data.local.NewsDB
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesNewsDB(application: Application): NewsDB {
        return Room.databaseBuilder(application, NewsDB::class.java, "News_Database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsDao(newsDB: NewsDB): NewsDao {
        return newsDB.newsDao
    }

    @Singleton
    @Provides
    fun providesNewsRepository(newsApiService: NewsApiService, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(newsApiService, newsDao)
    }

}

