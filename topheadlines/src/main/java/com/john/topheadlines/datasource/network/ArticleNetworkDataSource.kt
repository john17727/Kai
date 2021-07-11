package com.john.topheadlines.datasource.network

import com.john.shared.utils.TOKEN
import com.john.topheadlines.data.service.ArticleApiService
import com.john.topheadlines.data.service.ArticleNetworkService
import com.john.topheadlines.datasource.network.utils.ArticleResponseMapper
import com.john.topheadlines.domain.model.ArticleResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkDataSource
@Inject
constructor(
    private val articleApiService: ArticleApiService,
    private val articleResponseMapper: ArticleResponseMapper
): ArticleNetworkService {

    override suspend fun getTopHeadlines(country: String, page: Int): ArticleResponse {
        return articleResponseMapper.mapFromEntity(
            articleApiService.getTopHeadlines(
                TOKEN,
                country,
                page
            )
        )
    }
}