package com.kvlg.recipe.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.model.data.RecipeResponseModel
import com.kvlg.recipe.ui.event.RecipeListEvent
import com.kvlg.recipe.ui.event.RecipeListEvent.NewSearchEvent
import com.kvlg.recipe.ui.event.RecipeListEvent.NextPageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private var page = 1
    private var recipeListScrollPosition = 0
    var categoryScrollPosition = 0

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, "onEvent: ", throwable)
    }

    init {
        onEvent(NewSearchEvent)
    }

    fun onEvent(event: RecipeListEvent) {
        viewModelScope.launch(handler) {
            when (event) {
                is NewSearchEvent -> newSearch()
                is NextPageEvent -> nextPage()
            }
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

    fun onChangeScrollPosition(position: Int) {
        recipeListScrollPosition = position
        if (position + 1 >= page * PAGE_SIZE && !loading.value) {
            onEvent(NextPageEvent)
        }
    }

    private suspend fun newSearch() {
        loading.value = true
        resetSearchState()
        delay(5000)
        val result = repository.search(token, 1, query.value)
        recipes.value = result
        loading.value = false
    }

    private suspend fun nextPage() {
        //prevent duplicate events due to recompose happening to quickly
        loading.value = true
        page += 1
        Log.d(TAG, "nextPage: triggered: $page")

        delay(1000)

        if (page > 1) {
            val result = repository.search(
                token = token,
                page = page,
                query = query.value
            )
            Log.d(TAG, "nextPage: $result")
            appendRecipes(result)
        }
        loading.value = false
    }

    private fun resetSearchState() {
        recipes.value = emptyList()
        page = 1
        onChangeScrollPosition(0)
        if (selectedCategory.value?.value != query.value) {
            selectedCategory.value = null
        }
    }

    private fun appendRecipes(recipes: List<RecipeResponseModel>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    companion object {
        private const val TAG = "RecipeViewModel"
        private const val PAGE_SIZE = 30
    }
}