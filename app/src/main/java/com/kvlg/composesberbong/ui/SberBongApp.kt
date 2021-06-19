package com.kvlg.composesberbong.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.kvlg.composesberbong.ui.components.SberBongScaffold
import com.kvlg.composesberbong.ui.theme.SberBongTheme

/**
 * @author Konstantin Koval
 * @since 19.06.2021
 */

@Composable
fun SberBongApp() {
    ProvideWindowInsets {
        SberBongTheme {
            val navController = rememberNavController()
            SberBongScaffold(
                bottomBar = {

                }) {

            }
        }
    }
}