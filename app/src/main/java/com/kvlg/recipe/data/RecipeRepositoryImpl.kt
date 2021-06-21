package com.kvlg.recipe.data

import com.kvlg.recipe.model.data.RecipeResponseModel
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeService: RecipeService
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<RecipeResponseModel> {
        val result = recipeService.search(token, page, query).results
        return result.orEmpty()
    }

    override suspend fun get(token: String, id: Int): RecipeResponseModel {
        return recipeService.get(token, id)
    }
}