package com.kvlg.recipe.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.FoodCategory
import com.kvlg.recipe.ui.RecipeViewModel
import kotlinx.coroutines.launch

/**
 * @author Konstantin Koval
 * @since 29.06.2021
 */

@Composable
fun SearchAppBar(
    viewModel: RecipeViewModel
) {
    Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, elevation = 8.dp) {
        Column {
            Row {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = viewModel.query.value,
                    onValueChange = viewModel::onQueryChanged,
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
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                rememberCoroutineScope().launch { scrollState.animateScrollTo(value = viewModel.categoryScrollPosition) }
                FoodCategory.values().forEach {
                    FoodCategoryChip(
                        category = it,
                        isSelected = viewModel.selectedCategory.value == it,
                        onSelectCategoryChanged = { categoryName ->
                            viewModel.onSelectedCategoryChanged(categoryName)
                            viewModel.categoryScrollPosition = scrollState.value
                        },
                        onClick = {
                            viewModel.onQueryChanged(it.value)
                            viewModel.newSearch()
                        }
                    )
                }
            }
        }
    }
}