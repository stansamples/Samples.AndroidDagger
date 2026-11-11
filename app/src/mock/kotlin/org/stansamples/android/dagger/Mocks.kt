package org.stansamples.android.dagger

import org.stansamples.android.dagger.entity.Quote

internal object Mocks {
    var quotes: List<Quote> = (0 until 16).map { index ->
        Quote(
            id = index.toString(),
            value = "quote: $index",
        )
    }
}
