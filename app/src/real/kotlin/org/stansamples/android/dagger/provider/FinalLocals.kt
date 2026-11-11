package org.stansamples.android.dagger.provider

import android.content.Context
import org.stansamples.android.dagger.entity.Quote
import org.stansamples.android.dagger.BuildConfig

internal class FinalLocals(
    context: Context,
    private val coders: Coders,
) : Locals {
    private val prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    override var quotes: List<Quote>
        get() {
            return prefs.getStringSet("quotes", emptySet())?.map { coders.quotes.decode(it.toByteArray()) }.orEmpty()
        }
        set(value) {
            prefs.edit()
                .putStringSet("quotes", value.mapTo(mutableSetOf()) { String(coders.quotes.encode(it)) })
                .commit()
        }
}
