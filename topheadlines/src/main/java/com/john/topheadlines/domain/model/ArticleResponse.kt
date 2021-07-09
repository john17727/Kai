package com.john.topheadlines.domain.model

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
