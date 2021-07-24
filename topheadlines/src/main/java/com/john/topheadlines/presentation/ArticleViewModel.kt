package com.john.topheadlines.presentation

import android.util.Log
import com.john.mvi.domain.state.DataState
import com.john.mvi.domain.state.StateEvent
import com.john.mvi.presentation.BaseViewModel
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.state.TopHeadlinesStateEvent
import com.john.topheadlines.domain.state.TopHeadlinesStateEvent.GetTopHeadlinesEvent
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.john.topheadlines.interactors.TopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel
@Inject
constructor(
    private val topHeadlinesUseCase: TopHeadlinesUseCase
) : BaseViewModel<TopHeadlinesViewState>() {
    override fun handleNewData(data: TopHeadlinesViewState) {
        data.numArticles?.let {
            setTotalArticles(it)
        }

        data.articleList?.let { articleList ->
            setHeadlinesListData(articleList)
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<TopHeadlinesViewState>?> = when (stateEvent) {
            is GetTopHeadlinesEvent -> {
                topHeadlinesUseCase.getTopHeadlines(
                    country = stateEvent.country,
                    page = getPage(),
                    stateEvent = stateEvent
                )
            }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): TopHeadlinesViewState {
        return TopHeadlinesViewState()
    }

    fun loadFirstPage() {
        setArticlesExhausted(false)
        resetPage()
        setStateEvent(GetTopHeadlinesEvent("us", 1))
    }

    fun resetPage() {
        val update = getCurrentViewStateOrNew()
        update.page = 1
        setViewState(update)
    }


    // Getters
    private fun getPage() = getCurrentViewStateOrNew().page ?: 1


    //Setters
    private fun setTotalArticles(total: Int) {
        val update = getCurrentViewStateOrNew()
        if (update.numArticles != total) {
            update.numArticles = total
            setViewState(update)
        }
    }

    private fun setHeadlinesListData(headlines: ArrayList<Article>) {
        val update = getCurrentViewStateOrNew()
        update.articleList = headlines
        setViewState(update)
    }

    fun setArticlesExhausted(isExhausted: Boolean) {
        val update = getCurrentViewStateOrNew()
        update.areArticlesExhausted = isExhausted
        setViewState(update)
    }
}