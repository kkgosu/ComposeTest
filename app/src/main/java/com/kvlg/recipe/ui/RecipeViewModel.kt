package com.kvlg.recipe.ui

import androidx.lifecycle.ViewModel
import com.kvlg.recipe.data.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Konstantin Koval
 * @since 22.06.2021
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
}