package org.stansamples.android.dagger.module.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.BuildConfig
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Button
import org.stansamples.android.dagger.util.Text

internal object MainScreen {
    enum class State {
        Settings,
        Quotes,
        RandomQuote,
    }
}

@Composable
internal fun MainScreen(
    toState: (MainScreen.State) -> Unit,
) {
    val colors = Colors.Local.current
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
            Button(
                text = "settings",
                onClick = {
                    toState(MainScreen.State.Settings)
                },
            )
            Button(
                text = "quotes",
                onClick = {
                    toState(MainScreen.State.Quotes)
                },
            )
            Button(
                text = "random quote",
                onClick = {
                    toState(MainScreen.State.RandomQuote)
                },
            )
            val text = """
                ${BuildConfig.APPLICATION_ID}
                ${BuildConfig.VERSION_NAME}-${BuildConfig.VERSION_CODE}
            """.trimIndent()
            Text(text = text)
        }
    }
}
