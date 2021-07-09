package com.john.topheadlines.domain.state

import com.john.mvi.domain.state.StateEvent

sealed class TopHeadlinesStateEvent: StateEvent {

    class GetTopHeadlinesEvent(val country: String) : TopHeadlinesStateEvent() {
        override fun errorInfo(): String {
            return "Could not fetch news at this time"
        }

        override fun eventName(): String {
            return "GetTopHeadlinesEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}
