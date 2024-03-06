package com.example.newsapp.presentation.detailScreen.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.newsapp.R
import com.example.newsapp.data.model.Article
import com.example.newsapp.presentation.bookmarkScreen.viewmodel.BookMarkViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
    bookMarkViewModel: BookMarkViewModel = hiltViewModel(),
    navigate: () -> Unit
) {

    val localContext = LocalContext.current

    LaunchedEffect(key1 = bookMarkViewModel.screenToastState.value) {
        if (bookMarkViewModel.screenToastState.value.toastMessage.isNotBlank()) {
            Toast.makeText(
                localContext,
                bookMarkViewModel.screenToastState.value.toastMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {

        DetailTopAppBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW, Uri.parse(article.url)).let {
                    localContext.startActivity(it)
                }
            },
            onShareClick = { },
            onBookMarkClick = {
                bookMarkViewModel.saveArticle(article)
            },
            onBackClick = navigate,
            isSaved = article.isSaved
        )
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            item {
                GlideImage(
                    model = article.urlToImage,
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.Crop,
                    loading = placeholder(R.color.black),
                    failure = placeholder(R.color.black)
                )
                Spacer(modifier = modifier.height(19.dp))

                Text(
                    text = article.title ?: "",
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = article.content ?: "",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                )


            }

        }


    }


}

