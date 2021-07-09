package com.john.topheadlines.domain.network

data class ArticleNetworkEntity(
    val source: SourceNetworkEntity,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
