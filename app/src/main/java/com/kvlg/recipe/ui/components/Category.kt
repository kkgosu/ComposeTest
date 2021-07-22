package com.kvlg.recipe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.kvlg.recipe.R
import com.kvlg.recipe.ui.list.FoodCategory
import com.kvlg.recipe.ui.theme.RecipeTheme
import kotlin.math.max

/**
 * @author Konstantin Koval
 * @since 22.07.2021
 */

private val MinImageSize = 134.dp
private val CategoryShape = RoundedCornerShape(10.dp)
private const val CategoryTextProportion = 0.55f

@Composable
fun SearchCategory(
    category: FoodCategory,
    gradient: List<Color>,
    modifier: Modifier = Modifier
) {
    Layout(modifier = modifier
        .aspectRatio(1.45f)
        .shadow(elevation = 4.dp, shape = CategoryShape)
        .clip(CategoryShape)
        .background(Brush.horizontalGradient(gradient))
        .clickable { },
        content = {
            Text(
                text = category.value,
                style = MaterialTheme.typography.subtitle1,
                color = RecipeTheme.colors.textSecondary,
                modifier = Modifier
                    .padding(4.dp)
                    .padding(start = 8.dp)
            )
            RecipeImage(imageUrl = "", contentDescription = null, modifier = Modifier.fillMaxSize())
        }
    ) { measurables, constraints ->
        // Text given a set proportion of width (which is determined by the aspect ratio)
        val textWidth = (constraints.maxWidth * CategoryTextProportion).toInt()
        val textPlaceable = measurables[0].measure(Constraints.fixedWidth(textWidth))

        // Image is sized to the larger of height of item, or a minimum value
        // i.e. may appear larger than item (but clipped to the item bounds)
        val imageSize = max(MinImageSize.roundToPx(), constraints.maxHeight)
        val imagePlaceable = measurables[1].measure(Constraints.fixed(imageSize, imageSize))
        layout(
            width = constraints.maxWidth,
            height = constraints.minHeight
        ) {
            textPlaceable.placeRelative(
                x = 0,
                y = (constraints.maxHeight - textPlaceable.height) / 2 // centered
            )
            imagePlaceable.placeRelative(
                // image is placed to end of text i.e. will overflow to the end (but be clipped)
                x = textWidth,
                y = (constraints.maxHeight - imagePlaceable.height) / 2 // centered
            )
        }
    }
}

@Composable
fun RecipeImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    RecipeSurface(
        modifier = modifier,
        shape = CircleShape,
        color = Color.LightGray,
        elevation = elevation
    ) {
        Image(
            painter = rememberCoilPainter(request = imageUrl, previewPlaceholder = R.drawable.empty_plate),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}