package com.kvlg.recipe.data

import com.kvlg.recipe.model.data.RecipeResponseModel

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */
interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<RecipeResponseModel>

    suspend fun get(token: String, id: Int): RecipeResponseModel
}