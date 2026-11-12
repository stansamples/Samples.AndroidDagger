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
import org.stansamples.android.dagger.provider.Contexts
import org.stansamples.android.dagger.provider.Locals
import org.stansamples.android.dagger.provider.Loggers
import javax.inject.Inject

@HiltViewModel
internal class QuotesViewModel @Inject constructor(
    private val contexts: Contexts,
    private val loggers: Loggers,
    private val locals: Locals,
) : ViewModel(CoroutineScope(contexts.main + SupervisorJob())) {
    private val logger = loggers.create("[Quotes]")
    private val _quotes = MutableStateFlow<List<Quote>?>(null)
    val quotes: StateFlow<List<Quote>?> = _quotes.asStateFlow()

    fun requestQuotes() = viewModelScope.launch {
        logger.debug("request quotes")
        _quotes.value = withContext(contexts.io) {
            locals.quotes
        }
    }

    fun deleteQuote(id: String) = viewModelScope.launch {
        logger.debug("delete quote: $id")
        _quotes.value = withContext(contexts.io) {
            val quotes = locals.quotes.toMutableList()
            for (i in quotes.indices) {
                val quote = quotes[i]
                if (quote.id == id) {
                    quotes.removeAt(i)
                    break
                }
            }
            locals.quotes = quotes
            quotes
        }
    }
}
