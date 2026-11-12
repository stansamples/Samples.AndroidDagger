package org.stansamples.android.dagger

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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

        private val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return modelClass
                    .getConstructor(Providers::class.java)
                    .newInstance(providers)
            }
        }

        @Composable
        inline fun <reified T : ViewModel> viewModel(key: String = T::class.java.name): T {
            return viewModel(
                key = key,
                factory = factory,
            )
        }
    }
}
