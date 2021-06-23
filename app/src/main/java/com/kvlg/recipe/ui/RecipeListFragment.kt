package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kvlg.recipe.ui.components.RecipeCard

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    LazyColumn {
        itemsIndexed(
            items = viewModel.recipes.value
        ) { index, item ->
            RecipeCard(recipe = item, onClick = onRecipeClick)
        }
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recipe List", style = TextStyle(fontSize = 21.sp))
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { onRecipeClick(1) }) {
            Text(text = "To Recipe Fragment")
        }
    }
}