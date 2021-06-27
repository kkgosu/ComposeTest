package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.components.RecipeCard

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    Column {
        TextField(value = viewModel.query.value, onValueChange = viewModel::onQueryChange)
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn {
            itemsIndexed(
                items = viewModel.recipes.value
            ) { index, item ->
                RecipeCard(recipe = item, onClick = onRecipeClick)
            }
        }
    }
}