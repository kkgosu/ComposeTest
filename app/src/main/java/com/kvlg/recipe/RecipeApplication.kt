package com.kvlg.recipe

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */
@HiltAndroidApp
class RecipeApplication : Application() {
    companion object {
        val isDark = mutableStateOf(false)
        fun toggleLightTheme() {
            isDark.value = !isDark.value
        }
    }
}