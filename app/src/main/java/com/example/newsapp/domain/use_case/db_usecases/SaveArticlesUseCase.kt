package com.example.newsapp.domain.use_case.db_usecases

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SaveArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) {
        newsRepository.saveArticle(article)
    }

}