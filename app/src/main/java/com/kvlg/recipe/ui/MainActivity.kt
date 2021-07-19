package com.kvlg.recipe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.kvlg.recipe.RecipeApplication
import com.kvlg.recipe.ui.theme.AppTheme
import com.kvlg.recipe.ui.util.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val connectionLiveData = ConnectionLiveData(this)
        setContent {
            val isNetworkAvailable = connectionLiveData.observeAsState(false).value
            AppTheme(darkTheme = RecipeApplication.isDark.value, isNetworkAvailable = isNetworkAvailable) {
                RecipeAppNavGraph()
            }
        }
    }
}