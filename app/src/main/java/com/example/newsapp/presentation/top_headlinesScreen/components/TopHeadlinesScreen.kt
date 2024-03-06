package com.example.newsapp.presentation.top_headlinesScreen.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.top_headlinesScreen.viewmodel.TopHeadlinesViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun TopHeadLineScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewmodel: TopHeadlinesViewModel = hiltViewModel()
) {
    val state = viewmodel.state.value
    val pagerState = viewmodel._pagerState.value
    // todo: add any news using viewpager in this screen top and then impliment bnv and other screens

    LaunchedEffect(key1 = true) {
        viewmodel.getTopHeadlines()
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        state.articleList?.let {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = state.topHeadlinesTxt,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                )

                Spacer(modifier = modifier.height(8.dp))
                pagerState.articleList?.let {
                    TopHeadlinePager(
                        articles = it,
                        pageCount = pagerState.pageSize,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { article ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "articles",
                            article
                        )
                        navController.navigate(route = Screen.NewsDetailScreen.route)

                    }
                }

                Spacer(modifier = modifier.height(8.dp))


                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(it) {
                        Log.d("article", it.url.toString())
                        TopHeadlinesListitem(
                            article = it,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { article ->
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "articles",
                                    article
                                )
                                navController.navigate(route = Screen.NewsDetailScreen.route)
                            }
                        )

                    }

                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )
        }



        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)

            )
        }


    }

}