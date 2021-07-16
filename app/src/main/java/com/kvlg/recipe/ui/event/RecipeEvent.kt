package com.kvlg.recipe.ui.event

/**
 * @author Konstantin Koval
 * @since 16.07.2021
 */
sealed class RecipeEvent {
    data class GetRecipe(
        val id: Int
    ) : RecipeEvent()
}
