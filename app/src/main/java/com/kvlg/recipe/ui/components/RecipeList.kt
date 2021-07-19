package com.kvlg.recipe.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.RecipeListViewModel

/**
 * @author Konstantin Koval
 * @since 15.07.2021
 */

@Composable
fun RecipeList(
    listViewModel: RecipeListViewModel,
    onRecipeClick: (Int) -> Unit,
) {
    if (listViewModel.loading.value && listViewModel.recipes.value.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = 260.dp)
    }

    LazyColumn {
        itemsIndexed(
            items = listViewModel.recipes.value
        ) { index, item ->
            listViewModel.onChangeScrollPosition(index)
            RecipeCard(recipe = item, onClick = onRecipeClick)
        }

    }
}