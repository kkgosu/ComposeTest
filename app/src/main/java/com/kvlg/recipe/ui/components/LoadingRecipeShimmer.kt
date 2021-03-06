package com.kvlg.recipe.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author Konstantin Koval
 * @since 18.07.2021
 */

@Composable
fun LoadingRecipeShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val cardWidthPx = with(LocalDensity.current) {
            (maxWidth - (padding * 2)).toPx()
        }
        val cardHeightPx = with(LocalDensity.current) {
            (imageHeight - padding).toPx()
        }
        val gradientWidth = (0.2f * cardHeightPx)
        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f, targetValue = cardWidthPx + gradientWidth, animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        val yCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f, targetValue = cardHeightPx + gradientWidth, animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        val colors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.9f),
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            val brush = Brush.linearGradient(
                colors = colors,
                start = Offset(xCardShimmer.value - gradientWidth, yCardShimmer.value - gradientWidth),
                end = Offset(xCardShimmer.value, yCardShimmer.value)
            )
            Column(modifier = Modifier.padding(padding)) {
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight / 10)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight / 10)
                            .background(brush = brush)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}