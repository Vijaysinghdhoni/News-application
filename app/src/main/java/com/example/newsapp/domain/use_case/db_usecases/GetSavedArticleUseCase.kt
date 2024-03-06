package com.example.newsapp.domain.use_case.db_usecases

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    fun execute() = newsRepository.getAllBookMArkArticles()
}