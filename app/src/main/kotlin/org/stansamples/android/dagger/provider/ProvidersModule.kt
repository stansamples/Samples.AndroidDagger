package org.stansamples.android.dagger.provider

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.stansamples.android.dagger.entity.ThemeState
import org.stansamples.android.dagger.module.theme.Colors
import javax.inject.Singleton

@Module
internal object ProvidersModule {
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

    @Provides
    @Singleton
    fun locals(context: Context, coders: Coders): Locals = FinalLocals(context = context, coders = coders)

    @Provides
    @Singleton
    fun remotes(coders: Coders): Remotes = FinalRemotes(coders = coders)
}
