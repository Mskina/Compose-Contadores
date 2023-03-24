package com.example.composecounters.ui.navigation

sealed class Screens(val route: String){
    object MenuScreen: Screens("initial_screen")

    object Version01: Screens("ej01")
    object Version02: Screens("ej02")
    object Version03: Screens("ej03")
    object Version04: Screens("ej04")
    object VersionExtra01: Screens("extra01")

}
