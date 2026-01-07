package com.example.braindump.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.braindump.ui.screen.DumpScreen
import com.example.braindump.ui.screen.FavoriteScreen
import com.example.braindump.ui.screen.FeedbackScreen
import com.example.braindump.ui.screen.HomeScreen
import com.example.braindump.ui.screen.SettingScreen


@Composable
fun AppNavGraph(nav: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = nav,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) { HomeScreen(nav) }
        composable(Routes.DUMP) { DumpScreen(nav) }
        composable(Routes.FAVORITE) { FavoriteScreen(nav) }
        composable(Routes.SETTING) { SettingScreen(nav) }
        composable(Routes.FEEDBACK) { FeedbackScreen(nav) }
    }
}