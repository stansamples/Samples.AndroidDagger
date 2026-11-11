package org.stansamples.android.dagger.provider

import okhttp3.OkHttpClient
import okhttp3.Request
import org.stansamples.android.dagger.entity.Quote
import kotlin.time.Duration.Companion.seconds

internal class FinalRemotes(
    private val coders: Coders,
) : Remotes {
    private val client = OkHttpClient.Builder()
        .connectTimeout(10.seconds)
        .readTimeout(10.seconds)
        .writeTimeout(10.seconds)
        .build()

    override fun getRandomQuote(): Quote {
        val request = Request.Builder()
            .url("https://api.chucknorris.io/jokes/random")
            .build()
        return client.newCall(request).execute().use { response ->
            when (val code = response.code) {
                200 -> coders.quotes.decode(response.body.bytes())
                else -> error("Unknown code: $code")
            }
        }
    }
}
