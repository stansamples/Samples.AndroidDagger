package org.stansamples.android.dagger

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.stansamples.android.dagger.entity.ThemeState
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.provider.Coders
import org.stansamples.android.dagger.provider.Contexts
import org.stansamples.android.dagger.provider.FinalCoders
import org.stansamples.android.dagger.provider.FinalLocals
import org.stansamples.android.dagger.provider.FinalLoggers
import org.stansamples.android.dagger.provider.Flows
import org.stansamples.android.dagger.provider.Locals
import org.stansamples.android.dagger.provider.Loggers
import org.stansamples.android.dagger.provider.Providers

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val context: Context = this
        val contexts = Contexts(
            main = Dispatchers.Main,
            default = Dispatchers.Default,
            io = Dispatchers.IO,
        )
        val loggers: Loggers = FinalLoggers()
        val themeState = ThemeState(
            colors = Colors.Dark,
        )
        val flows: Flows = Flows(
            themes = MutableStateFlow(themeState),
        )
        val coders: Coders = FinalCoders()
        val locals: Locals = FinalLocals(
            context = context,
            coders = coders,
        )
        _providers = Providers(
            contexts = contexts,
            loggers = loggers,
            flows = flows,
            locals = locals,
        )
    }

    companion object {
        private var _providers: Providers? = null
        val providers: Providers get() = checkNotNull(_providers) { "No providers!" }

        private val _viewModels = mutableMapOf<Pair<String, Class<*>>, ViewModelStore>()
        private val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return modelClass
                    .getConstructor(Providers::class.java)
                    .newInstance(providers)
            }
        }

        @Composable
        inline fun <reified T : ViewModel> viewModel(label: String = T::class.java.name): T {
            val key = label to T::class.java
            val contains = remember { _viewModels.containsKey(key) }
            val store = _viewModels.getOrPut(key, ::ViewModelStore)
            val viewModel = ViewModelProvider(store, factory)[label, T::class.java]
            DisposableEffect(Unit) {
                onDispose {
                    if (!contains) _viewModels.remove(key)?.clear()
                }
            }
            return viewModel
        }

    }
}
