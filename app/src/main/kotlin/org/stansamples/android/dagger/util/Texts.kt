package org.stansamples.android.dagger.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.stansamples.android.dagger.module.theme.Colors

@Composable
internal fun Text(
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    textColor: Color = Colors.Local.current.texts.primary,
    backColor: Color = Colors.Local.current.backgrounds.secondary,
) {
    BasicText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(backColor, RoundedCornerShape(16.dp))
            .padding(16.dp),
        text = text,
        style = TextStyle(
            color = textColor,
            textAlign = textAlign,
            fontSize = 16.sp,
        ),
    )
}
