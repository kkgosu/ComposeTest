package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(onRecipeClick: (Long) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recipe List", style = TextStyle(fontSize = 21.sp))
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { onRecipeClick(1) }) {
            Text(text = "To Recipe Fragment")
        }
    }
}