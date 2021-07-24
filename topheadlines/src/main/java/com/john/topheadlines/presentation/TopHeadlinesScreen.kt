package com.john.topheadlines.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TopHeadlinesScreen(
    state: TopHeadlinesViewState,
    isLoading: Boolean,
    onRefresh: () -> Unit
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
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isLoading),
                onRefresh = onRefresh,
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        // Pass the SwipeRefreshState + trigger through
                        state = state,
                        refreshTriggerDistance = trigger,
                        // Enable the scale animation
                        scale = true,
                        // Change the color and shape
                        contentColor = MaterialTheme.colors.onSecondary,
                        backgroundColor = MaterialTheme.colors.secondary,
                        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(state.articleList) { index, article ->
                        ArticleCard(
                            article = article,
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
//                                vertical = 16.dp
                            )
                        )
                        if (index < state.articleList.size-1) {
                            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(vertical = 8.dp)) {
        Card {
            GlideImage(imageModel = article.urlToImage, contentScale = ContentScale.FillWidth)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
        Spacer(modifier = Modifier.height(16.dp))
        if (article.description.isNotEmpty()) {
            Text(text = article.description, color = MaterialTheme.colors.onSurface)
        }
    }
}