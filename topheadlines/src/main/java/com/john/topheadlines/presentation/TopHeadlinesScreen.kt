package com.john.topheadlines.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TopHeadlinesScreen(
    state: TopHeadlinesViewState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "Top Headlines",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.articleList) { article ->
                    ArticleCard(
                        article = article,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            GlideImage(imageModel = article.urlToImage)
            Text(
                text = article.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = article.timeSincePublished,
                    style = MaterialTheme.typography.body1
                )
            }
            Text(text = article.description, modifier = Modifier.padding(16.dp))
        }
    }
}