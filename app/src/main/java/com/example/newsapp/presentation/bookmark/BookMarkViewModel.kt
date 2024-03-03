package com.example.newsapp.presentation.bookmark

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.use_case.db_usecases.GetSavedNewsUseCase
import com.example.newsapp.domain.use_case.db_usecases.SaveArticlesUseCase
import com.example.newsapp.presentation.detail.DetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val saveArticlesUseCase: SaveArticlesUseCase
) :
    ViewModel() {

    var savedArticlesState = mutableStateOf(BookMarkScreenState())
        private set

    var screenToastState = mutableStateOf(DetailScreenState())
        private set

    fun getAllArticles() {

        getSavedNewsUseCase.execute().onEach {
            savedArticlesState.value = savedArticlesState.value.copy(articles = it)
        }.launchIn(viewModelScope)

    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            var arti = article
            arti = arti.copy(isSaved = true)
            saveArticlesUseCase.execute(arti)
            screenToastState.value = screenToastState.value.copy(toastMessage = "Article Saved")
        }
    }

    fun deleteArticles(article: Article) {

    }


}