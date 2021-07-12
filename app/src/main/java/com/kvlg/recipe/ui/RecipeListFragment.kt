package com.kvlg.recipe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.RecipeApplication.Companion.toggleLightTheme
import com.kvlg.recipe.ui.components.CircularIndeterminateProgressBar
import com.kvlg.recipe.ui.components.DefaultSnackbar
import com.kvlg.recipe.ui.components.LoadingRecipeListShimmer
import com.kvlg.recipe.ui.components.RecipeCard
import com.kvlg.recipe.ui.components.SearchAppBar
import com.kvlg.recipe.ui.util.SnackbarController

/**
 * @author Konstantin Koval
 * @since 21.06.2021
 */

@Composable
fun RecipeListFragment(viewModel: RecipeViewModel, onRecipeClick: (Long) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val snackbarController = SnackbarController(rememberCoroutineScope())

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        topBar = {
            SearchAppBar(
                viewModel = viewModel,
                scaffoldState = scaffoldState,
                snackbarController = snackbarController,
                onToggleTheme = { toggleLightTheme() })
        },
        bottomBar = {},
        drawerContent = {})
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            if (viewModel.loading.value && viewModel.recipes.value.isEmpty()) {
                LoadingRecipeListShimmer(imageHeight = 260.dp)
            }
            LazyColumn {
                itemsIndexed(
                    items = viewModel.recipes.value
                ) { index, item ->
                    viewModel.onChangeScrollPosition(index)
                    RecipeCard(recipe = item, onClick = onRecipeClick)
                }

            }
            CircularIndeterminateProgressBar(isDisplayed = viewModel.loading.value)
            DefaultSnackbar(snackbarHostState = scaffoldState.snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter)) {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }
/*        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), horizontalArrangement = Arrangement.Center
        ) {
            val state = remember { mutableStateOf(IDLE) }
            AnimatedHeartButton(modifier = Modifier, buttonState = state, onToggle = { state.value = if (state.value == IDLE) ACTIVE else IDLE })
        }*/
}