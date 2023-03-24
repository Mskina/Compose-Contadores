package com.example.composecounters.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecounters.ui.screens.MenuScreen
import com.example.composecounters.ui.screens.Version01Screen
import com.example.composecounters.ui.screens.Version02Screen
import com.example.composecounters.ui.screens.Version03Screen
import com.example.composecounters.ui.screens.Version04Screen
import com.example.composecounters.ui.screens.VersionExtra01

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.MenuScreen.route) {

        composable(route = Screens.MenuScreen.route) { MenuScreen(navController) }

        composable(route = Screens.Version01.route) { Version01Screen() }
        composable(route = Screens.Version02.route) { Version02Screen() }
        composable(route = Screens.Version03.route) { Version03Screen() }
        composable(route = Screens.Version04.route) { Version04Screen() }
        composable(route = Screens.VersionExtra01.route) { VersionExtra01() }

  }
}



