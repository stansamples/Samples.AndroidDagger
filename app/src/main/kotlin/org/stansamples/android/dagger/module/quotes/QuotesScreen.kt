package org.stansamples.android.dagger.module.quotes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.stansamples.android.dagger.App
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun QuotesScreen(
    onBack: () -> Unit,
) {
    val colors = Colors.Local.current
    val viewModel = App.viewModel<QuotesViewModel>()
    val quotes = viewModel.quotes.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.requestQuotes()
    }
    BackHandler(onBack = onBack)
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
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .background(colors.backgrounds.secondary, RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        text = quote.value,
                        style = TextStyle(
                            color = colors.texts.primary,
                            fontSize = 16.sp,
                        ),
                    )
                }
            }
        }
    }
}
