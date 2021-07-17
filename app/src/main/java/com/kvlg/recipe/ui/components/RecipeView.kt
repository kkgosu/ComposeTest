package com.kvlg.recipe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.kvlg.recipe.R
import com.kvlg.recipe.model.data.RecipeResponseModel

/**
 * @author Konstantin Koval
 * @since 18.07.2021
 */
@Composable
fun RecipeView(
    recipe: RecipeResponseModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        recipe.featuredImage?.let {
            Image(
                painter = rememberCoilPainter(request = it, fadeIn = true, previewPlaceholder = R.drawable.empty_plate),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(260.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(8.dp)) {
            recipe.title?.let {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
                recipe.publisher?.let {
                    val updateText = recipe.dateUpdated
                    Text(
                        text = if (updateText != null) "Updated $updateText by $it" else "By $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                recipe.ingredients.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}