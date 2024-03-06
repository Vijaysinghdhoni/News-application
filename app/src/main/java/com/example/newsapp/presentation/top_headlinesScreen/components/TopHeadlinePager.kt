package com.example.newsapp.presentation.top_headlinesScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.newsapp.R
import com.example.newsapp.data.model.Article

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun TopHeadlinePager(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    pageCount: Int,
    onItemClick: (Article) -> Unit
) {

    val pageState = rememberPagerState(pageCount = {
        pageCount
    })
    HorizontalPager(state = pageState) {
        val article = articles[it]
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = modifier.clickable {
                onItemClick(article)
            }
        ) {


            Column(
            ) {

                GlideImage(
                    model = article.urlToImage,
                    contentDescription = "news headline image",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    loading = placeholder(R.color.black),
                    failure = placeholder(R.color.black)
                )

                Spacer(modifier = modifier.height(12.dp))

                Column(modifier.padding(horizontal = 8.dp)) {
                    Text(
                        text = article.title ?: " ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }


                Spacer(modifier = modifier.height(10.dp))

            }


        }

    }

}

