package org.stansamples.android.dagger.provider

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.stansamples.android.dagger.entity.ThemeState
import org.stansamples.android.dagger.module.theme.Colors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CommonModule {
    @Provides
    @Singleton
    fun coders(): Coders = FinalCoders()

    @Provides
    @Singleton
    fun contexts(): Contexts = Contexts(
        main = Dispatchers.Main,
        default = Dispatchers.Default,
        io = Dispatchers.IO,
    )

    @Provides
    @Singleton
    fun loggers(): Loggers = FinalLoggers()

    @Provides
    @Singleton
    fun flows(): Flows = Flows(themes = MutableStateFlow(ThemeState(colors = Colors.Dark)))
}
