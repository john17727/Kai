package com.john.topheadlines.presentation

import com.john.mvi.domain.state.DataState
import com.john.mvi.presentation.compose.BaseViewModel
import com.john.topheadlines.domain.state.TopHeadlinesStateEvent
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.john.topheadlines.interactors.TopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel
@Inject
constructor(
    private val topHeadlinesUseCase: TopHeadlinesUseCase
) : BaseViewModel<TopHeadlinesStateEvent, TopHeadlinesViewState>() {

    init {
        setEvent(TopHeadlinesStateEvent.GetTopHeadlinesEvent("us", 1))
    }

    override fun setInitialState() = TopHeadlinesViewState()

    override fun handleNewState(data: TopHeadlinesViewState) {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: TopHeadlinesStateEvent) {
        val job: Flow<DataState<TopHeadlinesViewState>?> = when (event) {
            is TopHeadlinesStateEvent.GetTopHeadlinesEvent -> {
                topHeadlinesUseCase.getTopHeadlines(
                    country = event.country,
                    page = event.page,
                    stateEvent = event
                )
            }
            else -> {
                emitInvalidStateEvent(event)
            }
        }
        launchJob(event, job)
    }
}