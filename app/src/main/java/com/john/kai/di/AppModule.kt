package com.john.kai.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.john.shared.utils.BASE_URL
import com.john.shared.utils.DateUtil
import com.john.topheadlines.data.service.ArticleApiService
import com.john.topheadlines.data.service.ArticleNetworkService
import com.john.topheadlines.datasource.network.ArticleNetworkDataSource
import com.john.topheadlines.datasource.network.utils.ArticleNetworkMapper
import com.john.topheadlines.datasource.network.utils.ArticleResponseMapper
import com.john.topheadlines.datasource.network.utils.SourceNetworkMapper
import com.john.topheadlines.interactors.TopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideArticleApiService(retrofitBuilder: Retrofit.Builder): ArticleApiService {
        return retrofitBuilder.build().create(ArticleApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }

    @Singleton
    @Provides
    fun provideDateUtil(dateTimeFormatter: DateTimeFormatter): DateUtil {
        return DateUtil(dateTimeFormatter)
    }

    @Singleton
    @Provides
    fun provideArticleNetworkMapper(
        dateUtil: DateUtil,
        sourceNetworkMapper: SourceNetworkMapper
    ): ArticleNetworkMapper {
        return ArticleNetworkMapper(dateUtil, sourceNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideArticleResponseMapper(articleNetworkMapper: ArticleNetworkMapper): ArticleResponseMapper {
        return ArticleResponseMapper(articleNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideSourceNetworkMapper(): SourceNetworkMapper {
        return SourceNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideArticleNetworkService(
        articleApiService: ArticleApiService,
        articleResponseMapper: ArticleResponseMapper
    ): ArticleNetworkService {
        return ArticleNetworkDataSource(articleApiService, articleResponseMapper)
    }

    @Singleton
    @Provides
    fun provideGetTopHeadlines(
        articleNetworkDataSource: ArticleNetworkDataSource
    ): TopHeadlinesUseCase {
        return TopHeadlinesUseCase(articleNetworkDataSource)
    }
}