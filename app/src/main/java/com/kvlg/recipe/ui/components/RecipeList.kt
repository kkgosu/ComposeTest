package com.kvlg.recipe.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.RecipeViewModel

/**
 * @author Konstantin Koval
 * @since 15.07.2021
 */

@Composable
fun RecipeList(
    viewModel: RecipeViewModel,
    scaffoldState: ScaffoldState,
    modifier: Modifier,
    onRecipeClick: (Long) -> Unit,
) {
    if (viewModel.loading.value && viewModel.recipes.value.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = 260.dp)
    }

    LazyColumn {
        itemsIndexed(
            items = viewModel.recipes.value
        ) { index, item ->
            viewModel.onChangeScrollPosition(index)
            RecipeCard(recipe = item, onClick = onRecipeClick)
        }

    }
    CircularIndeterminateProgressBar(isDisplayed = viewModel.loading.value)
    DefaultSnackbar(snackbarHostState = scaffoldState.snackbarHostState, modifier = modifier) {
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
    }
}