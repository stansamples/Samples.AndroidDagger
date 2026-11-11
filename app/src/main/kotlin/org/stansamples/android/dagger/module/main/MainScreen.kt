package org.stansamples.android.dagger.module.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Button

internal object MainScreen {
    enum class State {
        Settings,
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
        }
    }
}
