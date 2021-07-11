package com.john.topheadlines.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.john.topheadlines.domain.model.Article

@Composable
fun TopHeadlinesScreen(
    articles: ArrayList<Article>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ArticleCard(article)
        }
    }
}

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(article.title)
    }
}