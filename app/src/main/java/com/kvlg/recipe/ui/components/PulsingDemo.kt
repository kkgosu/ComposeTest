package com.kvlg.recipe.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

/**
 * @author Konstantin Koval
 * @since 30.06.2021
 */

@Composable
fun PulseDemo() {
    val color = MaterialTheme.colors.primary
    val infiniteTransition = rememberInfiniteTransition()
    val pulseState = infiniteTransition.animateFloat(
        initialValue = 40f, targetValue = 50f, animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = Icons.Default.Favorite,
            modifier = Modifier
                .align(CenterVertically)
                .height(pulseState.value.dp)
                .width(pulseState.value.dp),
            contentDescription = null
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .height(56.dp)
    ) {
        drawCircle(
            radius = pulseState.value,
            brush = SolidColor(color)
        )
    }
}