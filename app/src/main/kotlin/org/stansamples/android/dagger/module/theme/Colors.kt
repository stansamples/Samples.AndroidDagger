package org.stansamples.android.dagger.module.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal sealed interface Colors {
    data class Backgrounds(
        val primary: Color,
        val secondary: Color,
    )

    data class Texts(
        val primary: Color,
        val secondary: Color,
    )

    val backgrounds: Backgrounds
    val texts: Texts

    data object Light : Colors {
        override val backgrounds = Backgrounds(
            primary = Color(0xfff0f0f0),
            secondary = Color(0xffb0b0b0),
        )
        override val texts = Texts(
            primary = Color(0xff000000),
            secondary = Color(0xff404040),
        )
    }

    data object Dark : Colors {
        override val backgrounds = Backgrounds(
            primary = Color(0xff000000),
            secondary = Color(0xff404040),
        )
        override val texts = Texts(
            primary = Color(0xfff0f0f0),
            secondary = Color(0xffb0b0b0),
        )
    }

    companion object {
        val Local = staticCompositionLocalOf<Colors> { error("No colors!") }
    }
}
