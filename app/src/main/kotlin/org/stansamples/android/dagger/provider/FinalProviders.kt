package org.stansamples.android.dagger.provider

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProvidersModule::class])
internal interface FinalProviders : Providers {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): Providers
    }
}
