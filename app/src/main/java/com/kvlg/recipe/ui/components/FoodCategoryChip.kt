package com.kvlg.recipe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.FoodCategory

/**
 * @author Konstantin Koval
 * @since 28.06.2021
 */
@Composable
fun FoodCategoryChip(
    category: FoodCategory,
    onClick: (String) -> Unit,
) {
    Surface(modifier = Modifier.padding(end = 8.dp), elevation = 8.dp, shape = MaterialTheme.shapes.medium, color = MaterialTheme.colors.primary) {
        Row(modifier = Modifier.clickable(onClick = { onClick(category.value) })) {
            Text(text = category.value, style = MaterialTheme.typography.body2, color = Color.White, modifier = Modifier.padding(8.dp))
        }

    }
}