package com.kvlg.recipe.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.model.data.RecipeResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val query = mutableStateOf("beef")

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            val result = repository.search(token, 1, "chicken")
            recipes.value = result
        }
    }

    fun onQueryChange(query: String) {
        this.query.value = query
    }
}