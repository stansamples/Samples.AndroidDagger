package org.stansamples.android.dagger.module.quotes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.stansamples.android.dagger.App
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Button
import org.stansamples.android.dagger.util.Text

@Composable
internal fun RandomQuoteScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    val viewModel = App.viewModel<RandomQuoteViewModel>()
    val states = viewModel.states.collectAsState()
    val isLoading = viewModel.loadings.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.requestRandomQuote()
    }
    BackHandler(onBack = onBack)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        ) {
            if (isLoading) {
                Text(text = "loading", textColor = colors.texts.secondary, backColor = Color.Transparent)
            } else {
                states.value?.fold(
                    onSuccess = { state ->
                        Text(text = state.quote.value)
                        if (!state.isSaved) {
                            Button(
                                text = "save",
                                onClick = {
                                    viewModel.save(quote = state.quote)
                                },
                            )
                        }
                    },
                    onFailure = {
                        // noop
                    },
                )
                Button(
                    text = "request",
                    onClick = viewModel::requestRandomQuote,
                )
            }
        }
    }
}
