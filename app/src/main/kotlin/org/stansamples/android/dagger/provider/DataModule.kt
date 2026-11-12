package org.stansamples.android.dagger.provider

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun locals(@ApplicationContext context: Context, coders: Coders): Locals = FinalLocals(context = context, coders = coders)

    @Provides
    @Singleton
    fun remotes(coders: Coders): Remotes = FinalRemotes(coders = coders)
}
