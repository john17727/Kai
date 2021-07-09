package com.john.topheadlines.datasource.network.utils

import com.john.mvi.data.util.EntityMapper
import com.john.topheadlines.domain.model.ArticleResponse
import com.john.topheadlines.domain.network.ArticleResponseEntity

class ArticleResponseMapper
constructor(
    private val articleNetworkMapper: ArticleNetworkMapper
): EntityMapper<ArticleResponseEntity, ArticleResponse> {

    override fun mapFromEntity(entity: ArticleResponseEntity): ArticleResponse {
        return ArticleResponse(
            entity.status,
            entity.totalResults,
            articleNetworkMapper.mapFromEntityList(entity.articles)
        )
    }

    override fun mapToEntity(model: ArticleResponse): ArticleResponseEntity {
        return ArticleResponseEntity(
            model.status,
            model.totalResults,
            articleNetworkMapper.mapToEntityList(model.articles)
        )
    }
}