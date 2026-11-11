package org.stansamples.android.dagger.module.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.stansamples.android.dagger.entity.Quote
import org.stansamples.android.dagger.provider.Providers

internal class RandomQuoteViewModel(
    private val providers: Providers,
) : ViewModel(CoroutineScope(providers.contexts.main + SupervisorJob())) {
    private val logger = providers.loggers.create("[RandomQuote]")
    private val _states = MutableStateFlow<Result<Quote>?>(null)
    val states: StateFlow<Result<Quote>?> = _states.asStateFlow()

    fun requestRandomQuote() = viewModelScope.launch {
        logger.debug("request random quote")
        _states.value = withContext(providers.contexts.default) {
            runCatching {
                providers.remotes.getRandomQuote()
            }.onFailure { error ->
                logger.warning("request random quote error: $error")
            }
        }
    }
}
