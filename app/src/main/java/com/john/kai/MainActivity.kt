package com.john.kai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.john.kai.ui.theme.KaiTheme
import com.john.topheadlines.presentation.ArticleViewModel
import com.john.topheadlines.presentation.TopHeadlinesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val articleViewModel by viewModels<ArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleViewModel.loadFirstPage()
        setContent {
            KaiTheme {
                Surface {
                    TopHeadlinesMainScreen(articleViewModel)
                }
            }
        }
    }

    @Composable
    fun TopHeadlinesMainScreen(articleViewModel: ArticleViewModel) {
        val articleViewState by articleViewModel.viewState.observeAsState()
        Log.i("DEBUG", "TopHeadlinesMainScreen: ${articleViewState}")
        articleViewState?.articleList?.let {
            TopHeadlinesScreen(it)
        }
    }
}