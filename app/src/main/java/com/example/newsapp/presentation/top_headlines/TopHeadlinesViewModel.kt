package com.example.newsapp.presentation.top_headlines

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.comman.Constants
import com.example.newsapp.comman.Resource
import com.example.newsapp.domain.use_case.getTopHedlines.GetPagerTopHeadlinesUseCase
import com.example.newsapp.domain.use_case.getTopHedlines.GetTopHeadLinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val getTopHeadLinesUseCase: GetTopHeadLinesUseCase,
    private val getPagerTopHeadlinesUseCase: GetPagerTopHeadlinesUseCase
) :
    ViewModel() {

    private val _state = mutableStateOf(TopHeadLinesScreenState())
    val state: State<TopHeadLinesScreenState> = _state

    var _pagerState = mutableStateOf(PagerScreenState())
        private set



    init {
        getTopHeadlines()
        getPagerTopHeadlines()
    }

    fun getTopHeadlines() {

        getTopHeadLinesUseCase.execute().onEach { response ->

            when (response) {

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        articles = response.data,
                        articleList = response.data?.articles,
                        isLoading = false
                    )
                    android.util.Log.d("mytag", response.data?.articles.toString())
                    android.util.Log.d("mytag", response.message.toString())
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = response.message ?: "Unknown error!",
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)

    }

    fun getPagerTopHeadlines() {
        getPagerTopHeadlinesUseCase.execute().onEach {

            when (it) {

                is Resource.Success -> {
                    _pagerState.value = _pagerState.value.copy(
                        isLoading = false,
                        articleList = it.data?.articles,
                        pageSize = it.data?.articles?.size ?: 0
                    )
                }

                is Resource.Error -> {
                    _pagerState.value = _pagerState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Unkown error"
                    )
                }

                is Resource.Loading -> {
                    _pagerState.value = _pagerState.value.copy(
                        isLoading = true
                    )
                }


            }

        }.launchIn(viewModelScope)
    }

}