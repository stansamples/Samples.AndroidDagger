package org.stansamples.android.dagger

import android.app.Application
import kotlinx.coroutines.Dispatchers
import org.stansamples.android.dagger.provider.Contexts
import org.stansamples.android.dagger.provider.FinalLoggers
import org.stansamples.android.dagger.provider.Loggers
import org.stansamples.android.dagger.provider.Providers

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val contexts = Contexts(
            main = Dispatchers.Main,
            default = Dispatchers.Default,
            io = Dispatchers.IO,
        )
        val loggers: Loggers = FinalLoggers()
        _providers = Providers(
            contexts = contexts,
            loggers = loggers,
        )
    }

    companion object {
        private var _providers: Providers? = null
        val provider: Providers get() = checkNotNull(_providers) { "No providers!" }
    }
}
