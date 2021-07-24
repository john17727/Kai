package com.john.topheadlines.interactors

import com.john.mvi.data.network.ApiResponseHandler
import com.john.mvi.data.util.safeApiCall
import com.john.mvi.domain.message.MessageType
import com.john.mvi.domain.message.StateMessage
import com.john.mvi.domain.message.UIComponentType
import com.john.mvi.domain.state.DataState
import com.john.mvi.domain.state.StateEvent
import com.john.topheadlines.datasource.network.ArticleNetworkDataSource
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.model.ArticleResponse
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlinesUseCase
@Inject
constructor(
    private val articleNetworkDataSource: ArticleNetworkDataSource
) {

    val articleList: ArrayList<Article> = arrayListOf()

    fun getTopHeadlines(country: String, category: String, page: Int, stateEvent: StateEvent): Flow<DataState<TopHeadlinesViewState>> = flow {
        var updatedPage = page
        if (page <= 0) {
            updatedPage = 1
        }

        if (page == 1) articleList.clear() // Quick fix for list appending on refresh. Might want to look for new implementation of list appending

        val apiResult = safeApiCall(IO) {
            articleNetworkDataSource.getTopHeadlines(country, category, page)
        }

        // Handle Api result
        val response = object : ApiResponseHandler<TopHeadlinesViewState, ArticleResponse>(
            response = apiResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: ArticleResponse): DataState<TopHeadlinesViewState> {

                var message: String = ARTICLES_SUCCESS

                if (resultObj.articles.isEmpty()) {
                    message = NO_MORE_ARTICLES
                }

                articleList.addAll(resultObj.articles)

                return DataState.data(
                    message = StateMessage(
                        message = message,
                        uiComponentType = UIComponentType.None,
                        messageType = MessageType.Success
                    ),
                    data = TopHeadlinesViewState(
                        articleList = articleList,
                        page = updatedPage,
                        numArticles = resultObj.totalResults,
                        currentCategory = category
                    ),
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(response)
    }

    companion object {
        const val NO_MORE_ARTICLES = "No more articles"
        const val ARTICLES_SUCCESS = "Successfully retrieved articles"
    }
}