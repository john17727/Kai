package com.john.topheadlines.data.service

import com.john.topheadlines.domain.model.ArticleResponse

interface ArticleNetworkService {

    suspend fun getTopHeadlines(country: String, category: String, page: Int): ArticleResponse
}