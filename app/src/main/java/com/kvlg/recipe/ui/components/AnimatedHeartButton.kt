package com.kvlg.recipe.ui.components

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author Konstantin Koval
 * @since 02.07.2021
 */
@Composable
fun AnimatedHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
    onToggle: () -> Unit
) {

    val size by animateDpAsState(
        // Animation won't work if target value is always equal, this may be a jetpack compose bug?
        if (buttonState.value == HeartAnimationDefinition.HeartButtonState.ACTIVE) 50.1.dp else 50.dp,
        animationSpec = keyframes {
            durationMillis = 500
            HeartAnimationDefinition.expandedIconSize.at(100)
            HeartAnimationDefinition.idleIconSize.at(200)
        },
        finishedListener = {
            Log.d("DEBUG", "TEST ANIMATION")
        }
    )

    HeartButton(
        modifier = modifier,
        buttonState = buttonState,
        onToggle = onToggle,
        size = size
    )
}

@Composable
private fun HeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
    onToggle: () -> Unit,
    size: Dp
) {
    if (buttonState.value == HeartAnimationDefinition.HeartButtonState.ACTIVE) {
        Image(
            imageVector = Icons.Default.Favorite,
            modifier = modifier
                .size(size)
                .clickable(onClick = onToggle),
            contentDescription = ""
        )
    } else {
        Image(
            imageVector = Icons.Default.FavoriteBorder,
            modifier = modifier
                .size(size)
                .clickable(onClick = onToggle),
            contentDescription = ""
        )
    }
}

object HeartAnimationDefinition {

    enum class HeartButtonState { IDLE, ACTIVE }

    val idleIconSize = 50.dp
    val expandedIconSize = 80.dp
}