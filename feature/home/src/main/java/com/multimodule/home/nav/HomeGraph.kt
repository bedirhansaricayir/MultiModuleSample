package com.multimodule.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.multimodule.home.ui.DetailScreen
import com.multimodule.home.ui.ListScreen
import com.multimodule.navigator.Route
import com.multimodule.navigator.Screen


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

fun NavGraphBuilder.homeGraph(
    onAction: (id: Int, name: String) -> Unit
) {
    navigation(
        startDestination = Screen.ListScreen.route,
        route = Route.Home.name
    ) {
        composable(Screen.ListScreen.route) {
            ListScreen(onAction = onAction)
        }

        composable(Screen.DetailScreen.route,
            arguments = listOf(
                navArgument("itemId")
                { type = NavType.IntType },
                navArgument("name") {
                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            val satelliteName = backStackEntry.arguments?.getString("name")
            if (itemId != null && satelliteName != null) {
                DetailScreen(itemId = itemId, name = satelliteName)
            }
        }
    }
}