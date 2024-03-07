package com.example.newsapp.domain.use_case.db_usecases

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend fun delete(article: Article) {
        repository.deleteArticle(article)
    }
}