package com.kvlg.recipe.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author Konstantin Koval
 * @since 11.07.2021
 */
@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(hostState = snackbarHostState, modifier = modifier) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                it.actionLabel?.let { label ->
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = label, style = MaterialTheme.typography.body2, color = Color.White)

                    }
                }
            }
        ) {
            Text(text = it.message, style = MaterialTheme.typography.body2, color = Color.White)
        }
    }
}