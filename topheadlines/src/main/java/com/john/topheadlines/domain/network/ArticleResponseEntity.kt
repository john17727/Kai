package com.john.topheadlines.domain.network

data class ArticleResponseEntity(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleNetworkEntity>
)
