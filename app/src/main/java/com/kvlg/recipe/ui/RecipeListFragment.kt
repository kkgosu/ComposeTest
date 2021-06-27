package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.components.RecipeCard

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    Column {
        Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.primary, elevation = 8.dp) {
            Row {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = viewModel.query.value,
                    onValueChange = viewModel::onQueryChange,
                    maxLines = 1,
                    label = {
                        Text(text = "Search")
                    },
                    keyboardActions = KeyboardActions(onSearch = { viewModel.newSearch() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
                )
            }
        }
        LazyColumn {
            itemsIndexed(
                items = viewModel.recipes.value
            ) { index, item ->
                RecipeCard(recipe = item, onClick = onRecipeClick)
            }
        }
    }
}