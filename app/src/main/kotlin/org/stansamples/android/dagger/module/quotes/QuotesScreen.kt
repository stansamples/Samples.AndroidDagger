package org.stansamples.android.dagger.module.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.stansamples.android.dagger.entity.Quote
import org.stansamples.android.dagger.module.theme.Colors
import org.stansamples.android.dagger.util.Text

@Composable
internal fun QuotesScreen(
    quotes: List<Quote>,
    onDeleteQuote: (id: String) -> Unit,
) {
    val colors = Colors.Local.current
    val insets = WindowInsets.systemBars.asPaddingValues()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = insets,
        ) {
            quotes?.forEach { quote ->
                item(key = quote.id) {
                    Text(
                        text = quote.value,
                        onClick = {
                            onDeleteQuote(quote.id)
                        },
                    )
                }
            }
        }
    }
}
