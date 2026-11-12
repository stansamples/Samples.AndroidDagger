package org.stansamples.android.dagger.module.quotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
internal fun QuotesRoute() {
    val viewModel = hiltViewModel<QuotesViewModel>()
    val quotes = viewModel.quotes.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.requestQuotes()
    }
    QuotesScreen(
        quotes = quotes.orEmpty(),
        onDeleteQuote = viewModel::deleteQuote,
    )
}
