package com.kvlg.recipe.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.model.data.RecipeResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Konstantin Koval
 * @since 22.06.2021
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val token: String
) : ViewModel() {
    val recipes: MutableState<List<RecipeResponseModel>> = mutableStateOf(ArrayList())
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val query = mutableStateOf("beef")
    val loading = mutableStateOf(false)

    var categoryScrollPosition = 0

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(5000)
            val result = repository.search(token, 1, query.value)
            recipes.value = result
            loading.value = false
        }
    }

    private fun resetSearchState() {
        recipes.value = emptyList()
        if (selectedCategory.value?.value != query.value) {
            selectedCategory.value = null
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }
}