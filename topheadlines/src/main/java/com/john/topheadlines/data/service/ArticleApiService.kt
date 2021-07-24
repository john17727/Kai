package com.john.topheadlines.data.service

import com.john.topheadlines.domain.network.ArticleResponseEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("Authorization") token: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int
    ): ArticleResponseEntity
}