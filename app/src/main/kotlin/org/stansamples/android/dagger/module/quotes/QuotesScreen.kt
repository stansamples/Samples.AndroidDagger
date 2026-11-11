package org.stansamples.android.dagger.module.quotes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.App
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun QuotesScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    val viewModel = App.viewModel<QuotesViewModel>()
    val quotes = viewModel.quotes.collectAsState().value
    BackHandler(onBack = onBack)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        // todo
    }
}
