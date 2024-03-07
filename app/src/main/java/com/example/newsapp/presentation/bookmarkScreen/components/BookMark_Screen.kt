package com.example.newsapp.presentation.bookmarkScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val localContext = LocalContext.current

    LaunchedEffect(key1 = true) {
        bookMarkViewModel.getAllArticles()
    }

    LaunchedEffect(key1 = bookMarkViewModel.savedArticlesState.value.isDelete) {
        if (bookMarkViewModel.savedArticlesState.value.toast.isNotBlank()) {
            Toast.makeText(
                localContext,
                bookMarkViewModel.savedArticlesState.value.toast,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        items(
            state.articles,
            key = {
                it.id
            }
        ) { article ->
            SwipeToDeleteContainer(
                item = article,
                onDelete = {
                    bookMarkViewModel.deleteArticles(article)
                }
            ) { art ->
                BookMarkArticleItem(article = art) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "articles",
                        it
                    )
                    navController.navigate(Screen.NewsDetailScreen.route)
                }

            }


        }
    }


}