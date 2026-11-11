package org.stansamples.android.dagger.module.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.provider.Providers

internal class SettingsViewModel(
    private val providers: Providers,
) : ViewModel(CoroutineScope(providers.contexts.main + SupervisorJob())) {
    fun switchColors() = viewModelScope.launch {
        withContext(providers.contexts.io) {
            val themeState = providers.flows.themes.value
            val colors = when (themeState.colors) {
                Colors.Dark -> Colors.Light
                Colors.Light -> Colors.Dark
            }
            providers.flows.themes.value = themeState.copy(colors = colors)
        }
    }
}
