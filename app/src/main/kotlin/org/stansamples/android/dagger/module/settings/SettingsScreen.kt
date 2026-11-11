package org.stansamples.android.dagger.module.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun SettingsScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    BackHandler(onBack = onBack)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        // todo
    }
}
