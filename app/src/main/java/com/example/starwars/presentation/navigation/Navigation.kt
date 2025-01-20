package com.example.starwars.presentation.navigation;

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.starwars.domain.model.CharacterModel
import com.example.starwars.presentation.features.detail.DetailScreen
import com.example.starwars.presentation.features.detail.DetailVM
import com.example.starwars.presentation.features.main.MainScreen
import com.example.starwars.presentation.features.main.MainVM
import com.google.gson.Gson

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            val entry = remember(it) {
                navController.getBackStackEntry(Screen.MainScreen.route)
            }
            val mainVM = hiltViewModel<MainVM>(entry)
            MainScreen(navController = navController, viewModel = mainVM)
        }
        composable(
            route = "${Screen.DetailScreen.route}/{characterJson}",
            arguments = listOf(navArgument("characterJson") {
                type = NavType.StringType
                nullable = false
            })
        ) {
            val entry = remember(it) {
                navController.getBackStackEntry("${Screen.DetailScreen.route}/{characterJson}")
            }
            val characterJson = entry.arguments?.getString("characterJson")
            if (characterJson != null) {
                val character = Gson().fromJson(characterJson, CharacterModel::class.java)
                val detailVM = hiltViewModel<DetailVM>(entry)
                DetailScreen(character)
            }
        }
    }

}