package org.stansamples.android.dagger.module.router

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.stansamples.android.dagger.module.theme.Colors

@Composable
private fun Button(
    text: String,
    onClick: () -> Unit,
) {
    val colors = Colors.Local.current
    BasicText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(colors.backgrounds.secondary, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        text = text,
        style = TextStyle(
            color = colors.texts.primary,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        ),
    )
}

@Composable
internal fun RouterScreen() {
    val colors = Colors.Local.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgrounds.primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        ) {
            Button(
                text = "settings",
                onClick = {
                    // todo
                },
            )
        }
    }
}
