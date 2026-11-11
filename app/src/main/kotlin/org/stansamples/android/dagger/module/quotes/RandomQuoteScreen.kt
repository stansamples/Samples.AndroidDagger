package org.stansamples.android.dagger.module.quotes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.stansamples.android.dagger.App
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Button

@Composable
internal fun RandomQuoteScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    val viewModel = App.viewModel<RandomQuoteViewModel>()
    val states = viewModel.states.collectAsState()
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
            states.value?.fold(
                onSuccess = { quote ->
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .background(colors.backgrounds.secondary, RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        text = quote.value,
                        style = TextStyle(
                            color = colors.texts.primary,
                            fontSize = 16.sp,
                        ),
                    )
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
