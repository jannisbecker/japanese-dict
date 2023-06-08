package moe.mokacchi.japanesedict.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import moe.mokacchi.japanesedict.ui.screens.camera.CameraScreen
import moe.mokacchi.japanesedict.ui.screens.search.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(onNavigateToCameraScreen = {
                navController.navigate("camera")
            })
        }

        composable("camera") {
            CameraScreen()
        }
    }
}