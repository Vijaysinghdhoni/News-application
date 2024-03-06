package com.example.newsapp.presentation.bookmarkScreen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.bookmarkScreen.viewmodel.BookMarkViewModel

@Composable
fun BookMarkScreen(
    modifier: Modifier = Modifier,
    bookMarkViewModel: BookMarkViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = bookMarkViewModel.savedArticlesState.value

    LaunchedEffect(key1 = true) {
        bookMarkViewModel.getAllArticles()
    }

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(vertical = 15.dp, horizontal = 20.dp)) {
        items(state.articles) { article ->
            BookMarkArticleItem(article = article) {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "articles",
                    it
                )
                navController.navigate(Screen.NewsDetailScreen.route)
            }
        }
    }


}