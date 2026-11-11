package org.stansamples.android.dagger

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import org.stansamples.android.dagger.provider.DaggerFinalProviders
import org.stansamples.android.dagger.provider.Providers

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        _providers = DaggerFinalProviders.factory().create(this)
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
