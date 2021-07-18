package com.kvlg.recipe.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kvlg.recipe.ui.components.DefaultSnackbar
import com.kvlg.recipe.ui.components.LoadingRecipeShimmer
import com.kvlg.recipe.ui.components.RecipeView
import com.kvlg.recipe.ui.event.RecipeEvent.GetRecipe
import com.kvlg.recipe.ui.util.SnackbarController

@Composable
fun RecipeFragment(id: Int, viewModel: RecipeViewModel) {
    viewModel.onEvent(GetRecipe(id))
    val scaffoldState = rememberScaffoldState()
    val snackbarController = SnackbarController(rememberCoroutineScope())

    Scaffold(scaffoldState = scaffoldState, snackbarHost = { scaffoldState.snackbarHostState }) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (viewModel.loading.value && viewModel.recipe.value == null) {
                LoadingRecipeShimmer(imageHeight = 260.dp)
            } else {
                viewModel.recipe.value?.let {
                    if (id == 1) {
                        snackbarController.showSnackbar(
                            scaffoldState = scaffoldState,
                            message = "An error occurred with this recipe",
                            actionLabel = "Ok"
                        )
                    } else {
                        RecipeView(recipe = it)
                    }
                }
            }
            DefaultSnackbar(snackbarHostState = scaffoldState.snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter)) {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

}