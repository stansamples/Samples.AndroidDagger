package org.stansamples.android.dagger.module.settings

import androidx.compose.runtime.Composable
import org.stansamples.android.dagger.App

@Composable
internal fun SettingsRoute() {
    val viewModel = App.viewModel<SettingsViewModel>()
    SettingsScreen(
        onSwitchColors = viewModel::switchColors,
    )
}
