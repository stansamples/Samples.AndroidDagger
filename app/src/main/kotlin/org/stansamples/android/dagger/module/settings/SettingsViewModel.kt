package org.stansamples.android.dagger.module.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.provider.Contexts
import org.stansamples.android.dagger.provider.Flows
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val contexts: Contexts,
    private val flows: Flows,
) : ViewModel(CoroutineScope(contexts.main + SupervisorJob())) {
    fun switchColors() = viewModelScope.launch {
        withContext(contexts.io) {
            val themeState = flows.themes.value
            val colors = when (themeState.colors) {
                Colors.Dark -> Colors.Light
                Colors.Light -> Colors.Dark
            }
            flows.themes.value = themeState.copy(colors = colors)
        }
    }
}
