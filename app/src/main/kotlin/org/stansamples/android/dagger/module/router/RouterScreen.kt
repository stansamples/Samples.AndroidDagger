package org.stansamples.android.dagger.module.router

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.module.main.MainScreen
import org.stansamples.android.dagger.module.settings.SettingsScreen
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun RouterScreen() {
    val colors = Colors.Local.current
    val states = remember { mutableStateOf<MainScreen.State?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        when (states.value) {
            MainScreen.State.Settings -> SettingsScreen(
                onBack = {
                    states.value = null
                },
            )
            null -> MainScreen(
                toState = { state ->
                    states.value = state
                },
            )
        }
    }
}
