package org.stansamples.android.dagger.provider

import org.stansamples.android.dagger.Mocks
import org.stansamples.android.dagger.entity.Quote

internal class FinalRemotes : Remotes {
    private var indices = 0

    override fun getRandomQuote(): Quote {
        Thread.sleep(1_000)
        indices = indices.plus(1) % Mocks.quotes.size
        return Mocks.quotes[indices]
    }
}
