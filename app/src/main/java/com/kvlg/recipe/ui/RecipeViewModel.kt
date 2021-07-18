package com.kvlg.recipe.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.model.data.RecipeResponseModel
import com.kvlg.recipe.ui.event.RecipeEvent
import com.kvlg.recipe.ui.event.RecipeEvent.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Konstantin Koval
 * @since 16.07.2021
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val token: String,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, "onEvent: $throwable", throwable.cause)
    }

    val recipe = mutableStateOf<RecipeResponseModel?>(null)
    val loading = mutableStateOf(false)

    init {
        savedStateHandle.get<Int>(STATE_KEY_RECIPE)
    }

    fun onEvent(event: RecipeEvent) {
        viewModelScope.launch(handler) {
            when (event) {
                is GetRecipe -> getRecipe(event.id)
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true
        delay(2000)
        val recipe = repository.get(token = token, id = id)
        this.recipe.value = recipe
        savedStateHandle.set(STATE_KEY_RECIPE, recipe.pk)
        loading.value = false
    }


    companion object {
        private const val TAG = "RecipeViewModel"
        private const val STATE_KEY_RECIPE = "recipe.state.recipe.key"
    }
}