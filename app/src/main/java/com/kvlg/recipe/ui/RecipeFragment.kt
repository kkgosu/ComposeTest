package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kvlg.recipe.ui.event.RecipeEvent.GetRecipe

@Composable
fun RecipeFragment(id: Int, viewModel: RecipeViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        viewModel.onEvent(GetRecipe(id))
        Text(text = viewModel.recipe.value?.let { "selected recipe title ${it.title}" } ?: "Loading...")
    }
}