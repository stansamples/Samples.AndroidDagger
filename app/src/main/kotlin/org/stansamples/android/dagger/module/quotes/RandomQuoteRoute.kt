package org.stansamples.android.dagger.module.quotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.stansamples.android.dagger.App

@Composable
internal fun RandomQuoteRoute() {
    val viewModel = App.viewModel<RandomQuoteViewModel>()
    val states = viewModel.states.collectAsState()
    val isLoading = viewModel.loadings.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.requestRandomQuote()
    }
    RandomQuoteScreen(
        result = states.value,
        isLoading = isLoading,
        onRequestRandomQuote = viewModel::requestRandomQuote,
        onSaveQuote = viewModel::save,
    )
}
