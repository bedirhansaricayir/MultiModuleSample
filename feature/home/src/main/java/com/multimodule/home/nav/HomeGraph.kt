package com.multimodule.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.multimodule.home.ui.DetailScreen
import com.multimodule.home.ui.ListScreen
import com.multimodule.navigator.Route
import com.multimodule.navigator.Screen


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

fun NavGraphBuilder.homeGraph(
    onAction:()->Unit
) {
    navigation(
        startDestination = Screen.ListScreen.route,
        route = Route.Home.name
    ) {
        composable(Screen.ListScreen.route) {
            ListScreen(onAction)
        }

        composable(Screen.DetailScreen.route) {
            DetailScreen()
        }
    }
}