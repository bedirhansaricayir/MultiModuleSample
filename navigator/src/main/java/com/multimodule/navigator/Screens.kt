package com.multimodule.navigator


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

sealed class Screen(
    val route: String,
) {
    object DetailScreen : Screen("detailScreen/{itemId}/{name}") {
        fun passIdAndName(
            id: Int,
            name: String
        ): String {
            return "detailScreen/$id/$name"
        }
    }

    object ListScreen : Screen("listScreen")

}

sealed class Route(val name: String) {
    object Home : Route("Home")
}