package com.john.kai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.john.kai.ui.theme.KaiTheme
import com.john.kai.ui.theme.KaiWhite
import com.john.topheadlines.domain.model.Article
import com.john.topheadlines.domain.state.TopHeadlinesViewState
import com.john.topheadlines.presentation.ArticleViewModel
import com.john.topheadlines.presentation.HeadlinesViewModel
import com.john.topheadlines.presentation.TopHeadlinesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val headlinesViewModel by viewModels<HeadlinesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TopHeadlinesMainScreen(headlinesViewModel)
                }
            }
        }
    }

    @Composable
    fun TopHeadlinesMainScreen(headlinesViewModel: HeadlinesViewModel) {
        val state = headlinesViewModel.viewState.value
        val isLoading = headlinesViewModel.isLoading.value
        TopHeadlinesScreen(state, isLoading, headlinesViewModel::refresh)
    }
}