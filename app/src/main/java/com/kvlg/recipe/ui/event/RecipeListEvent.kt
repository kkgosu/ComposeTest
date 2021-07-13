package com.kvlg.recipe.ui.event

/**
 * @author Konstantin Koval
 * @since 14.07.2021
 */
sealed class RecipeListEvent {
    object NewSearchEvent : RecipeListEvent()

    object NextPageEvent : RecipeListEvent()
}
