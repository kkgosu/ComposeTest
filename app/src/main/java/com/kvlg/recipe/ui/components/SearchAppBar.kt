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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kvlg.recipe.ui.FoodCategory
import com.kvlg.recipe.ui.list.RecipeListViewModel
import com.kvlg.recipe.ui.event.RecipeListEvent.NewSearchEvent
import com.kvlg.recipe.ui.util.SnackbarController
import kotlinx.coroutines.launch

/**
 * @author Konstantin Koval
 * @since 29.06.2021
 */

@Composable
fun SearchAppBar(
    listViewModel: RecipeListViewModel,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    onToggleTheme: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.secondary, elevation = 8.dp) {
        Column {
            Row {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = listViewModel.query.value,
                    onValueChange = listViewModel::onQueryChanged,
                    maxLines = 1,
                    label = {
                        Text(text = "Search")
                    },
                    keyboardActions = KeyboardActions(onSearch = { listViewModel.onEvent(NewSearchEvent) }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    textStyle = MaterialTheme.typography.button
                )
                ConstraintLayout(modifier = Modifier.align(Alignment.CenterVertically)) {
                    val menu = createRef()
                    IconButton(onClick = onToggleTheme, modifier = Modifier.constrainAs(menu) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                    }
                }
            }
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                rememberCoroutineScope().launch { scrollState.animateScrollTo(value = listViewModel.categoryScrollPosition) }
                FoodCategory.values().forEach {
                    FoodCategoryChip(
                        category = it,
                        isSelected = listViewModel.selectedCategory.value == it,
                        onSelectCategoryChanged = { categoryName ->
                            listViewModel.onSelectedCategoryChanged(categoryName)
                            listViewModel.categoryScrollPosition = scrollState.value
                        },
                        onClick = {
                            if (listViewModel.selectedCategory.value?.value == "Milk") {
                                snackbarController.showSnackbar(scaffoldState, "Invalid category", "Hide")
                            } else {
                                listViewModel.onQueryChanged(it.value)
                                listViewModel.onEvent(NewSearchEvent)
                            }
                        }
                    )
                }
            }
        }
    }
}