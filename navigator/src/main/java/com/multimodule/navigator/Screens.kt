package com.multimodule.navigator


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

sealed class Screen(
    val route: String,
) {
    object DetailScreen : Screen("detailScreen")
    object ListScreen : Screen("listScreen")

}

sealed class Route(val name: String) {
    object Home : Route("Home")
}