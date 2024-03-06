package com.example.newsapp.presentation.bookmark.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookMarkArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onNewsClick: (Article) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                onNewsClick(article)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = article.urlToImage,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentDescription = "Article image",
            contentScale = ContentScale.Crop,
            loading = placeholder(R.color.black),
            failure = placeholder(R.color.black)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = article.title ?: " ",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time_ic),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = article.publishedAt ?: "",
                    style = TextStyle(fontSize = 13.sp, color = Color.Black)
                )
            }
        }
    }
    Spacer(modifier = modifier.height(10.dp))


}