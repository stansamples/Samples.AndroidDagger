package org.stansamples.android.dagger.module.router

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.stansamples.android.dagger.module.main.MainDestination
import org.stansamples.android.dagger.module.main.MainScreen
import org.stansamples.android.dagger.module.quotes.QuotesDestination
import org.stansamples.android.dagger.module.quotes.QuotesRoute
import org.stansamples.android.dagger.module.quotes.RandomQuoteDestination
import org.stansamples.android.dagger.module.quotes.RandomQuoteRoute
import org.stansamples.android.dagger.module.settings.SettingsDestination
import org.stansamples.android.dagger.module.settings.SettingsRoute
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun RouterScreen() {
    val colors = Colors.Local.current
    val backStack = rememberNavBackStack(MainDestination)
    NavDisplay(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
        backStack = backStack,
        onBack = {
            backStack.removeAt(backStack.lastIndex)
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<MainDestination> {
                MainScreen(
                    toState = { state ->
                        backStack += when (state) {
                            MainScreen.State.Quotes -> QuotesDestination
                            MainScreen.State.RandomQuote -> RandomQuoteDestination
                            MainScreen.State.Settings -> SettingsDestination
                        }
                    },
                )
            }
            entry<QuotesDestination> {
                QuotesRoute()
            }
            entry<SettingsDestination> {
                SettingsRoute()
            }
            entry<RandomQuoteDestination> {
                RandomQuoteRoute()
            }
        },
    )
}
