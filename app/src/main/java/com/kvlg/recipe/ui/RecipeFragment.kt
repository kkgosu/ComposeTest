package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeFragment(id: Long) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recipe Fragment with ID = $id")
    }
}