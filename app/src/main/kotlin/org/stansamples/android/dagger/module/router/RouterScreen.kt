package org.stansamples.android.dagger.module.router

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun RouterScreen() {
    val colors = Colors.Local.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        // todo
    }
}
