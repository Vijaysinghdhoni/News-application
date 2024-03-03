package com.example.newsapp.domain.use_case.getTopHedlines

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadLinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    fun execute() = newsRepository.getTopHeadlines()

}