package com.john.topheadlines.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.john.topheadlines.R
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TopHeadlinesScreen(
    state: TopHeadlinesViewState,
    isLoading: Boolean,
    onRefreshOrCategoryChange: (String) -> Unit
) {
    val categories = stringArrayResource(R.array.categories)
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
            Column(modifier = Modifier.fillMaxSize()) {
                LazyRow {
                    itemsIndexed(categories) { index, category ->
                        val modifier = when (index) {
                            0 -> Modifier.padding(
                                start = 16.dp,
                                top = 4.dp,
                                end = 4.dp,
                                bottom = 4.dp
                            )
                            categories.size - 1 -> Modifier.padding(
                                start = 4.dp,
                                top = 4.dp,
                                end = 16.dp,
                                bottom = 4.dp
                            )
                            else -> Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
                        }
                        CategoryChip(
                            category = category,
                            onCategorySelected = onRefreshOrCategoryChange,
                            isSelected = category == state.currentCategory,
                            modifier = modifier
                        )
                    }
                }
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isLoading),
                    onRefresh = { onRefreshOrCategoryChange(state.currentCategory) },
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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(state.articleList) { index, article ->
                            ArticleCard(
                                article = article,
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                            if (index < state.articleList.size - 1) {
                                Divider(
                                    color = MaterialTheme.colors.onSurface,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 24.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
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

@Composable
fun CategoryChip(
    modifier: Modifier = Modifier,
    category: String,
    isSelected: Boolean = false,
    onCategorySelected: (String) -> Unit
) {
    Surface(
        modifier = modifier,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.surface,
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = { onCategorySelected(category) }
            )
        ) {
            Text(
                text = category,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                style = MaterialTheme.typography.body2,
                color = if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.secondary
            )
        }
    }
}