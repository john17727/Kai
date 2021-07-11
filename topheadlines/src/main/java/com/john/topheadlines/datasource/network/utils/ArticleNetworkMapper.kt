package com.john.topheadlines.datasource.network.utils

import com.john.mvi.data.util.EntityMapper
import com.john.shared.utils.DateUtil
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.network.ArticleNetworkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkMapper
@Inject
constructor(
    private val dateUtil: DateUtil,
    private val sourceNetworkMapper: SourceNetworkMapper
): EntityMapper<ArticleNetworkEntity, Article> {

    fun mapFromEntityList(entities: List<ArticleNetworkEntity>): List<Article> {
        val list = ArrayList<Article>()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun mapToEntityList(models: List<Article>): List<ArticleNetworkEntity> {
        val list = ArrayList<ArticleNetworkEntity>()
        for (model in models) {
            list.add(mapToEntity(model))
        }
        return list
    }

    override fun mapFromEntity(entity: ArticleNetworkEntity): Article {
        return Article(
            source = sourceNetworkMapper.mapFromEntity(entity.source),
            author = entity.author?:"",
            title = removeSourceTagFromTitle(entity.title),
            description = entity.description?:"",
            url = entity.url,
            urlToImage = entity.urlToImage?:"",
            publishedAt = entity.publishedAt,
            timeSincePublished = dateUtil.getPublishTimeSpan(entity.publishedAt),
            content = entity.content?:""
        )
    }

    private fun removeSourceTagFromTitle(title: String): String {
        if (title.contains(" - ")) {
            val hyphenTail = """\s-\s.*""".toRegex()
            return hyphenTail.replace(title, "")
        }
        return title
    }

    override fun mapToEntity(model: Article): ArticleNetworkEntity {
        return ArticleNetworkEntity(
            source = sourceNetworkMapper.mapToEntity(model.source),
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content
        )
    }
}