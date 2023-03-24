package com.example.composecounters.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composecounters.ui.navigation.Screens

@Composable
fun MenuScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { navController.navigate(route = Screens.Version01.route) }) {
            Text(text = "Versión básica")
        }
        Button(onClick = { navController.navigate(route = Screens.Version02.route) }) {
            Text(text = "Con State Hoisting")
        }
        Button(onClick = { navController.navigate(route = Screens.Version03.route) }) {
            Text(text = "Mejorada")
        }
        Button(onClick = { navController.navigate(route = Screens.Version04.route) }) {
            Text(text = "Variante alternativa")
        }
        Button(onClick = { navController.navigate(route = Screens.VersionExtra01.route) }) {
            Text(text = "VersionExtra01")
        }

    }

}