package com.kvlg.recipe.data

import com.kvlg.recipe.model.data.RecipeResponse
import com.kvlg.recipe.model.data.RecipeResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */
interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeResponseModel

    companion object {
        const val BASE_URL = "https://food2fork.ca/api/recipe"
    }
}