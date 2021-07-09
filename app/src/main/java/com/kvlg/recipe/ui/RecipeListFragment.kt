package com.kvlg.recipe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.RecipeApplication
import com.kvlg.recipe.RecipeApplication.Companion.toggleLightTheme
import com.kvlg.recipe.ui.components.CircularIndeterminateProgressBar
import com.kvlg.recipe.ui.components.LoadingRecipeListShimmer
import com.kvlg.recipe.ui.components.RecipeCard
import com.kvlg.recipe.ui.components.SearchAppBar

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
        SearchAppBar(viewModel = viewModel, onToggleTheme = { toggleLightTheme() })
        if (viewModel.loading.value) {
            LoadingRecipeListShimmer(imageHeight = 260.dp)
        }
/*        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), horizontalArrangement = Arrangement.Center
        ) {
            val state = remember { mutableStateOf(IDLE) }
            AnimatedHeartButton(modifier = Modifier, buttonState = state, onToggle = { state.value = if (state.value == IDLE) ACTIVE else IDLE })
        }*/
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                itemsIndexed(
                    items = viewModel.recipes.value
                ) { index, item ->
                    RecipeCard(recipe = item, onClick = onRecipeClick)
                }

            }
            CircularIndeterminateProgressBar(isDisplayed = viewModel.loading.value)
        }
    }
}