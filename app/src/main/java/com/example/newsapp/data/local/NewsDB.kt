package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.model.Article

@Database(entities = [(Article::class)], version = 2, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class NewsDB : RoomDatabase() {

    abstract val newsDao: NewsDao

}