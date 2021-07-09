package com.john.topheadlines.domain.model

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val timeSincePublished: String,
    val content: String
)
