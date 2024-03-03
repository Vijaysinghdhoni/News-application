package com.example.newsapp.domain.use_case.getTopHedlines

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetPagerTopHeadlinesUseCase @Inject constructor(private val repository :NewsRepository) {
    fun execute() = repository.getPagerTopHealines()
}