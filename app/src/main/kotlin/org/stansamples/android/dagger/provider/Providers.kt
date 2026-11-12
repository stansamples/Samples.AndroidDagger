package org.stansamples.android.dagger.provider

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class Providers @Inject constructor(
    val contexts: Contexts,
    val loggers: Loggers,
    val flows: Flows,
    val locals: Locals,
    val remotes: Remotes,
)
