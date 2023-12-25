package com.example.iss_tool

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: Int
) {
    object Home: BottomBarScreen(
        route ="home",
        label ="Home",
        icon = R.drawable.home_icon
    )
    object Info: BottomBarScreen(
        route ="info",
        label ="Info",
        icon = R.drawable.info_icon
    )
    object Settings: BottomBarScreen(
        route ="settings",
        label ="Settings",
        icon = R.drawable.settings_icon
    )
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingModifier: Modifier
) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route= BottomBarScreen.Home.route) {
            HomeScreen(paddingModifier)
        }
        composable(route= BottomBarScreen.Info.route) {
            InfoScreen(paddingModifier)
        }
        composable(route= BottomBarScreen.Settings.route) {
            SettingsScreen(paddingModifier)
        }
    }
}

@Composable
fun Show_logo(modifier: Modifier,id:Int,color: Color){
    Image(
        painter = painterResource(id = id),
        contentDescription = "WHO Logo",
        modifier = modifier
            .requiredWidth(width = 125.dp)
            .requiredHeight(height = 35.dp),
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)

    )
}