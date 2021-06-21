package com.kvlg.recipe.model.data

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<RecipeResponseModel>? = null
)

data class RecipeResponseModel(
    @SerializedName("cooking_instructions")
    val cookingInstructions: Any? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("date_updated")
    val dateUpdated: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("featured_image")
    val featuredImage: String? = null,
    @SerializedName("ingredients")
    val ingredients: List<String> = listOf(),
    @SerializedName("long_date_added")
    val longDateAdded: Int? = null,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int? = null,
    @SerializedName("pk")
    val pk: Int? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("rating")
    val rating: Int? = null,
    @SerializedName("source_url")
    val sourceUrl: String? = null,
    @SerializedName("title")
    val title: String? = null
)
