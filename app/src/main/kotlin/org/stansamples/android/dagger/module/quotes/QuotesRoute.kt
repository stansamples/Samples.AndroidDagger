package org.stansamples.android.dagger.module.quotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.stansamples.android.dagger.App

@Composable
internal fun QuotesRoute() {
    val viewModel = App.viewModel<QuotesViewModel>()
    val quotes = viewModel.quotes.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.requestQuotes()
    }
    QuotesScreen(
        quotes = quotes.orEmpty(),
        onDeleteQuote = viewModel::deleteQuote,
    )
}
