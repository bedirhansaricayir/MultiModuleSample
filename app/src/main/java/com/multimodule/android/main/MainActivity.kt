package com.multimodule.android.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.multimodule.home.nav.homeGraph
import com.multimodule.navigator.Route
import com.multimodule.navigator.Screen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.Home.name,
            ) {
                homeGraph { id, name ->
                    navController.navigate(Screen.DetailScreen.passIdAndName(id, name))
                }
            }
        }
    }
}