package com.kvlg.recipe.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.model.data.RecipeResponseModel
import com.kvlg.recipe.ui.event.RecipeListEvent
import com.kvlg.recipe.ui.event.RecipeListEvent.NewSearchEvent
import com.kvlg.recipe.ui.event.RecipeListEvent.NextPageEvent
import com.kvlg.recipe.ui.event.RecipeListEvent.RestoreStateEvent
import dagger.assisted.Assisted
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
    private val token: String,
    @Assisted private val savedStateHandle: SavedStateHandle
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
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let(::setPage)
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let(::setQuery)
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let(::setListScrollPosition)
        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let(::setSelectedCategory)

        if (recipeListScrollPosition != 0) {
            onEvent(RestoreStateEvent)
        } else {
            onEvent(NewSearchEvent)
        }
    }

    fun onEvent(event: RecipeListEvent) {
        viewModelScope.launch(handler) {
            when (event) {
                is NewSearchEvent -> newSearch()
                is NextPageEvent -> nextPage()
                is RestoreStateEvent -> restoreState()
            }
        }
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        onQueryChanged(category)
    }

    fun onChangeScrollPosition(position: Int) {
        setListScrollPosition(position)
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
        setPage(page + 1)
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
            setSelectedCategory(null)
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results = mutableListOf<RecipeResponseModel>()
        for (p in 1..page) {
            val result = repository.search(
                token = token,
                page = p,
                query = query.value
            )
            results.addAll(result)
            if (p == page) {
                recipes.value = results
                loading.value = false
            }
        }
    }

    private fun appendRecipes(recipes: List<RecipeResponseModel>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun setListScrollPosition(position: Int) {
        recipeListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: FoodCategory?) {
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }

    companion object {
        private const val TAG = "RecipeViewModel"
        private const val PAGE_SIZE = 30
        private const val STATE_KEY_PAGE = "recipe.state.page.key"
        private const val STATE_KEY_QUERY = "recipe.state.query.key"
        private const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
        private const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"
    }
}