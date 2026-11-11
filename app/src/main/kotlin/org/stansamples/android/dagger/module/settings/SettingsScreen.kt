package org.stansamples.android.dagger.module.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.App
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Button

@Composable
internal fun SettingsScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    val viewModel = App.viewModel<SettingsViewModel>()
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
            Button(
                text = "switch colors",
                onClick = viewModel::switchColors,
            )
        }
    }
}
