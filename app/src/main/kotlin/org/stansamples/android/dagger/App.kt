package org.stansamples.android.dagger

import android.app.Application
import kotlinx.coroutines.Dispatchers
import org.stansamples.android.dagger.provider.Contexts
import org.stansamples.android.dagger.provider.Providers

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val contexts = Contexts(
            main = Dispatchers.Main,
            default = Dispatchers.Default,
            io = Dispatchers.IO,
        )
        _providers = Providers(
            contexts = contexts,
        )
    }

    companion object {
        private var _providers: Providers? = null
        val provider: Providers get() = checkNotNull(_providers) { "No providers!" }
    }
}
