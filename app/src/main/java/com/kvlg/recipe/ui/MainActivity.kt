package com.kvlg.recipe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.ExperimentalUnitApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            RecipeAppNavGraph()
/*            Column(
                modifier = Modifier
                    .background(Color(0xFFF2F2F2))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.happy_meal_small),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Happy meal", style = TextStyle(fontSize = 24.sp))
                        Text(
                            text = "$5.99",
                            style = TextStyle(fontSize = 20.sp),
                            color = Color(0xFF85bb65),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "800 calories", style = TextStyle(fontSize = 20.sp))
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Order Now!")
                    }
                }
            }*/
        }
    }
}