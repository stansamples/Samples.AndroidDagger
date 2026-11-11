package org.stansamples.android.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import org.stansamples.android.dagger.module.router.RouterScreen
import org.stansamples.android.dagger.module.theme.Colors

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ComposeView(this)
        setContentView(view)
        view.setContent {
            CompositionLocalProvider(
                Colors.Local provides Colors.Dark,
            ) {
                RouterScreen()
            }
        }
    }
}
