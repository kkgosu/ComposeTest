package com.kvlg.recipe.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.kvlg.recipe.ui.Destinations.HOME
import com.kvlg.recipe.ui.Destinations.HOME_ROUTE
import com.kvlg.recipe.ui.Destinations.RECIPE_DETAILS
import com.kvlg.recipe.ui.Destinations.RECIPE_ID

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

object Destinations {
    const val HOME = "Home"
    const val HOME_ROUTE = "Home/Recipes"
    const val RECIPE_DETAILS = "Recipe details"
    const val RECIPE_ID = "Recipe id"
}

@Composable
fun RecipeAppNavGraph(
    recipeViewModel: RecipeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(route = HOME, startDestination = HOME_ROUTE) {
            composable(HOME_ROUTE) {
                RecipeListFragment(viewModel = recipeViewModel, onRecipeClick = { id ->
                    navController.navigate("$RECIPE_DETAILS/$id")
                })
            }
            composable(
                route = "$RECIPE_DETAILS/{$RECIPE_ID}",
                arguments = listOf(navArgument(RECIPE_ID) { type = NavType.LongType })
            ) { backStackEntry ->
                val args = requireNotNull(backStackEntry.arguments)
                val recipeId = args.getLong(RECIPE_ID)
                RecipeFragment(recipeId)
            }
        }
    }
}