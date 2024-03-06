package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.model.Article
import com.example.newsapp.presentation.bookmarkScreen.components.BookMarkScreen
import com.example.newsapp.presentation.detailScreen.components.NewsDetailScreen
import com.example.newsapp.presentation.navigation.NavigationViewModel
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.theme.NewsAppTheme
import com.example.newsapp.presentation.top_headlinesScreen.components.TopHeadLineScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NavigationViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val state = viewModel.navState.value

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(bottomBar = {
                        NavigationBar {
                            state.navItem.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = state.selectedItem == index,
                                    onClick = {
                                        viewModel.onChange(index)
                                        navController.navigate(item.title)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    alwaysShowLabel = true,
                                    icon = {
                                        Icon(
                                            imageVector = if (index == state.selectedItem) {
                                                item.selectedIcon
                                            } else {
                                                item.unSelectedItem
                                            }, contentDescription = null
                                        )
                                    })

                            }
                        }
                    }) {

                        NavHost(
                            navController = navController,
                            startDestination = Screen.TopHeadlinesScreen.route
                        ) {

                            composable(Screen.TopHeadlinesScreen.route) {
                                TopHeadLineScreen(navController = navController)
                            }

                            composable(Screen.BookMarkScreen.route) {
                                BookMarkScreen(navController = navController)
                            }

                            composable(
                                Screen.NewsDetailScreen.route
                            ) {
                                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>(
                                    "articles"
                                )?.let { article ->
                                    NewsDetailScreen(
                                        article = article,
                                        navigate = { navController.navigateUp() }
                                    )
                                }
                            }

                        }


                    }

                }
            }
        }
    }
}
