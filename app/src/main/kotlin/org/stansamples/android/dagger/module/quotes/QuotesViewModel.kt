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
internal class QuotesViewModel @Inject constructor(
    private val providers: Providers,
) : ViewModel(CoroutineScope(providers.contexts.main + SupervisorJob())) {
    private val logger = providers.loggers.create("[Quotes]")
    private val _quotes = MutableStateFlow<List<Quote>?>(null)
    val quotes: StateFlow<List<Quote>?> = _quotes.asStateFlow()

    fun requestQuotes() = viewModelScope.launch {
        logger.debug("request quotes")
        _quotes.value = withContext(providers.contexts.io) {
            providers.locals.quotes
        }
    }

    fun deleteQuote(id: String) = viewModelScope.launch {
        logger.debug("delete quote: $id")
        _quotes.value = withContext(providers.contexts.io) {
            val quotes = providers.locals.quotes.toMutableList()
            for (i in quotes.indices) {
                val quote = quotes[i]
                if (quote.id == id) {
                    quotes.removeAt(i)
                    break
                }
            }
            providers.locals.quotes = quotes
            quotes
        }
    }
}
