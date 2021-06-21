package com.kvlg.recipe.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RecipeResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<RecipeModel>?
)

@Parcelize
data class RecipeModel(
    val cooking_instructions: String? = null,
    val date_added: String? = null,
    val date_updated: String? = null,
    val description: String? = null,
    val featured_image: String? = null,
    val ingredients: List<String> = listOf(),
    val long_date_added: Int? = null,
    val long_date_updated: Int? = null,
    val pk: Int? = null,
    val publisher: String? = null,
    val rating: Int? = null,
    val source_url: String? = null,
    val title: String? = null
) : Parcelable