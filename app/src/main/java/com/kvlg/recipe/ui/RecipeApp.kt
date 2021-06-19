package com.kvlg.recipe.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.kvlg.recipe.ui.components.RecipeScaffold
import com.kvlg.recipe.ui.theme.RecipeTheme

/**
 * @author Konstantin Koval
 * @since 19.06.2021
 */

@Composable
fun RecipeApp() {
    ProvideWindowInsets {
        RecipeTheme {
            val navController = rememberNavController()
            RecipeScaffold(
                bottomBar = {

                }) {

            }
        }
    }
}