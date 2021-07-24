package com.john.topheadlines.domain.state

import com.john.mvi.domain.state.ViewState
import com.john.topheadlines.domain.model.Article

data class TopHeadlinesViewState(
    var articleList: ArrayList<Article> = arrayListOf(),
    var page: Int? = null,
    var numArticles: Int? = null,
    var areArticlesExhausted: Boolean = false,
    var currentCategory: String = "General",
): ViewState
