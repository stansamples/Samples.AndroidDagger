package org.stansamples.android.dagger.provider

import android.content.Context
import org.stansamples.android.dagger.entity.Quote
import org.stansamples.android.dagger.BuildConfig

internal class FinalLocals(context: Context) : Locals {
    private val prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    override var quotes: List<Quote>
        get() = TODO("Not yet implemented")
        set(value) {
            prefs.edit()
                .putStringSet("quotes", value.mapTo(mutableSetOf()) { "${it.id}:${it.value}" })
                .commit()
        }
}
