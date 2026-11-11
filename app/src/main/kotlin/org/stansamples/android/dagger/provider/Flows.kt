package org.stansamples.android.dagger.provider

import kotlinx.coroutines.flow.MutableStateFlow
import org.stansamples.android.dagger.entity.ThemeState

internal class Flows(
    val themes: MutableStateFlow<ThemeState>,
)
