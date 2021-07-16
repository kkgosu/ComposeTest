package com.kvlg.recipe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
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
 * @since 24.06.2021
 */

@Composable
fun RecipeCard(
    recipe: RecipeResponseModel,
    onClick: (Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                16.dp
            )
            .fillMaxWidth()
            .clickable(onClick = {
                onClick(recipe.pk ?: 0)
            }),
        elevation = 8.dp
    ) {
        Column {
            recipe.featuredImage?.let {
                Image(
                    painter = rememberCoilPainter(request = it, fadeIn = true, previewPlaceholder = R.drawable.empty_plate),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(225.dp),
                    contentScale = ContentScale.Crop
                )
            }
            recipe.title?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = recipe.rating?.toString() ?: "0",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}