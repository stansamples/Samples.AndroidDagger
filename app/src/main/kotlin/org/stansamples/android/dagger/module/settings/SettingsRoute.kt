package org.stansamples.android.dagger.module.settings

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
internal fun SettingsRoute() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    SettingsScreen(
        onSwitchColors = viewModel::switchColors,
    )
}
