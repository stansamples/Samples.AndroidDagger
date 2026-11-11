package org.stansamples.android.dagger.module.quotes

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.stansamples.android.dagger.provider.Providers

internal class RandomQuoteViewModel(
    private val providers: Providers,
) : ViewModel(CoroutineScope(providers.contexts.main + SupervisorJob())) {
    // todo
}
