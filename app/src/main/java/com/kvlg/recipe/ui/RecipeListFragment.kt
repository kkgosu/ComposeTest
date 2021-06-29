package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kvlg.recipe.ui.components.CircularIndeterminateProgressBar
import com.kvlg.recipe.ui.components.RecipeCard
import com.kvlg.recipe.ui.components.SearchAppBar

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    Column {
        SearchAppBar(viewModel = viewModel)
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                itemsIndexed(
                    items = viewModel.recipes.value
                ) { index, item ->
                    RecipeCard(recipe = item, onClick = onRecipeClick)
                }
            }
            CircularIndeterminateProgressBar(isDisplayed = viewModel.loading.value)
        }
    }
}