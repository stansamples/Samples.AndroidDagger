package org.stansamples.android.dagger.provider

import org.stansamples.android.dagger.entity.Quote

internal interface Remotes {
    fun getRandomQuote(): Quote
}
