package org.stansamples.android.dagger.provider

import org.json.JSONObject
import org.stansamples.android.dagger.entity.Quote

internal class FinalCoders : Coders {
    override val quotes = object : Coder<Quote> {
        override fun encode(decoded: Quote): ByteArray {
            return JSONObject()
                .put("id", decoded.id)
                .put("value", decoded.value)
                .toString()
                .toByteArray()
        }

        override fun decode(encoded: ByteArray): Quote {
            val obj = JSONObject(String(encoded))
            return Quote(
                id = obj.getString("id"),
                value = obj.getString("value"),
            )
        }
    }
}
