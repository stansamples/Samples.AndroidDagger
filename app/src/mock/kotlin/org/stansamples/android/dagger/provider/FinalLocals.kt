package org.stansamples.android.dagger.provider

import android.content.Context
import org.stansamples.android.dagger.Mocks
import org.stansamples.android.dagger.entity.Quote

internal class FinalLocals(
    context: Context,
    coders: Coders,
) : Locals {
    override var quotes: List<Quote>
        get() {
            return Mocks.quotes
        }
        set(value) {
            Mocks.quotes = value
        }
}
