package org.stansamples.android.dagger.module.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal sealed interface Colors {
    data class Backgrounds(
        val primary: Color,
    )

    data class Texts(
        val primary: Color,
    )

    val backgrounds: Backgrounds
    val texts: Texts

    data object Light : Colors {
        override val backgrounds = Backgrounds(
            primary = Color(0xffffffff),
        )
        override val texts = Texts(
            primary = Color(0xff000000),
        )
    }

    data object Dark : Colors {
        override val backgrounds = Backgrounds(
            primary = Color(0xff000000),
        )
        override val texts = Texts(
            primary = Color(0xffffffff),
        )
    }

    companion object {
        val Local = staticCompositionLocalOf<Colors> { error("No colors!") }
    }
}
