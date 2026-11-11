package org.stansamples.android.dagger.provider

import org.stansamples.android.dagger.Mocks
import org.stansamples.android.dagger.entity.Quote

internal class FinalLocals : Locals {
    override var quotes: List<Quote>
        get() {
            return Mocks.quotes
        }
        set(value) {
            Mocks.quotes = value
        }
}
