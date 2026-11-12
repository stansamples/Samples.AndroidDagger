package org.stansamples.android.dagger.module.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.stansamples.android.dagger.entity.Quote
import org.stansamples.android.dagger.provider.Providers
import javax.inject.Inject

@HiltViewModel
internal class RandomQuoteViewModel @Inject constructor(
    private val providers: Providers,
) : ViewModel(CoroutineScope(providers.contexts.main + SupervisorJob())) {
    class State(
        val isSaved: Boolean,
        val quote: Quote,
    )

    private val logger = providers.loggers.create("[RandomQuote]")
    private val _states = MutableStateFlow<Result<State>?>(null)
    val states: StateFlow<Result<State>?> = _states.asStateFlow()
    private val _loadings = MutableStateFlow<Boolean>(true)
    val loadings: StateFlow<Boolean> = _loadings

    fun requestRandomQuote() = viewModelScope.launch {
        logger.debug("request random quote")
        _loadings.value = true
        _states.value = withContext(providers.contexts.default) {
            runCatching {
                providers.remotes.getRandomQuote()
            }.onFailure { error ->
                logger.warning("request random quote error: $error")
            }.map { quote ->
                val isSaved = providers.locals.quotes.any { it.id == quote.id }
                State(
                    isSaved = isSaved,
                    quote = quote,
                )
            }
        }
        _loadings.value = false
    }

    fun save(quote: Quote) = viewModelScope.launch {
        logger.debug("save quote: ${quote.id}")
        _loadings.value = true
        _states.value = withContext(providers.contexts.default) {
            runCatching {
                providers.locals.quotes += quote
            }.onFailure { error ->
                logger.warning("save quote ${quote.id} error: $error")
            }.map {
                State(
                    isSaved = true,
                    quote = quote,
                )
            }
        }
        _loadings.value = false
    }
}
