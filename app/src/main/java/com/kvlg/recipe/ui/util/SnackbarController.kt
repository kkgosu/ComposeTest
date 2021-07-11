package com.kvlg.recipe.ui.util

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Konstantin Koval
 * @since 11.07.2021
 */
class SnackbarController(
    val scope: CoroutineScope
) {

    fun showSnackbar(
        scaffoldState: ScaffoldState,
        message: String,
        actionLabel: String
    ) {
        scope.launch {
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            scaffoldState.snackbarHostState.showSnackbar(message, actionLabel)
        }
    }
}