package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.model.Article
import com.example.newsapp.presentation.bookmark.components.BookMarkScreen
import com.example.newsapp.presentation.detail.NewsDetailScreen
import com.example.newsapp.presentation.theme.NewsAppTheme
import com.example.newsapp.presentation.top_headlines.components.TopHeadLineScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val list = listOf(
                    BottomNavItem(
                        title = Screen.TopHeadlinesScreen.route,
                        selectedIcon = Icons.Filled.Home,
                        unSelectedItem = Icons.Outlined.Home
                    ),
                    BottomNavItem(
                        title = "Bookmark",
                        selectedIcon = Icons.Filled.Favorite,
                        unSelectedItem = Icons.Outlined.FavoriteBorder
                    )
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(bottomBar = {
                        NavigationBar {
                            list.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.title)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    alwaysShowLabel = true,
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
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
