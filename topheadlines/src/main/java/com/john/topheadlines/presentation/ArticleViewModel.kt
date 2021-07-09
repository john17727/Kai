package com.john.topheadlines.presentation

import com.john.mvi.domain.state.DataState
import com.john.mvi.domain.state.StateEvent
import com.john.mvi.presentation.BaseViewModel
import com.john.topheadlines.domain.state.TopHeadlinesStateEvent
import com.john.topheadlines.domain.state.TopHeadlinesStateEvent.GetTopHeadlinesEvent
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.john.topheadlines.interactors.TopHeadlinesUseCase
import kotlinx.coroutines.flow.Flow

class ArticleViewModel
constructor(
    private val topHeadlinesUseCase: TopHeadlinesUseCase
) : BaseViewModel<TopHeadlinesViewState>() {
    override fun handleNewData(data: TopHeadlinesViewState) {
        TODO("Not yet implemented")
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

    // Getters
    private fun getPage() = getCurrentViewStateOrNew().page ?: 1
}