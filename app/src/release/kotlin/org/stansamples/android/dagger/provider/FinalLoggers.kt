package org.stansamples.android.dagger.provider

internal class FinalLoggers : Loggers {
    override fun create(tag: String): Logger {
        return FinalLogger
    }
}

private object FinalLogger : Logger {
    override fun debug(message: String) {
        // noop
    }

    override fun warning(message: String) {
        // noop
    }
}
