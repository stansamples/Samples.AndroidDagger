package org.stansamples.android.dagger.provider

import org.stansamples.android.dagger.entity.Quote

internal interface Coders {
    val quotes: Coder<Quote>
}

internal interface Coder<T : Any> {
    fun encode(decoded: T): ByteArray
    fun decode(encoded: ByteArray): T
}
