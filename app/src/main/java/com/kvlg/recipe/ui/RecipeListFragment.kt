package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.components.AnimatedHeartButton
import com.kvlg.recipe.ui.components.CircularIndeterminateProgressBar
import com.kvlg.recipe.ui.components.HeartAnimationDefinition.HeartButtonState.ACTIVE
import com.kvlg.recipe.ui.components.HeartAnimationDefinition.HeartButtonState.IDLE
import com.kvlg.recipe.ui.components.LoadingRecipeListShimmer
import com.kvlg.recipe.ui.components.RecipeCard
import com.kvlg.recipe.ui.components.SearchAppBar
import com.kvlg.recipe.ui.components.ShimmerRecipeCardItem

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    Column {
        SearchAppBar(viewModel = viewModel)
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