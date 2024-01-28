package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography

object HomeNavigation {
    const val MainHomeRoute = "main"
    const val ClassificationRoute = "classification"
}

fun NavGraphBuilder.homeGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = HomeNavigation.MainHomeRoute,
        route = BottomBarScreen.Home.route
    ) {
        composable(route = HomeNavigation.MainHomeRoute) {
            HomeScreen(navController = navController, modifier = modifier)
        }
        composable(
            route = "${HomeNavigation.ClassificationRoute}?unNumber={unNumber}",
            arguments = listOf(navArgument("unNumber") {
                nullable = true
            })
        ) { navBackStackEntry ->
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")
            ClassificationScreen(navController = navController, modifier = modifier, unNumber = unNumber)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    Column (
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Let's Get Started!",
            style = customTypography.bodyLarge,
            color = customColorScheme.primary)
        Text(text = "Choose your substance to be shipped",
            style = customTypography.bodyMedium,
            color = black
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    // use '-' as unNumber for Category Exempt
                    navController.navigate("${HomeNavigation.ClassificationRoute}?unNumber=2814") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }},
                text = "Infectious Substance Affecting Humans",
                unNumber = 2814
            )
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    // use '-' as unNumber for Category Exempt
                    navController.navigate("${HomeNavigation.ClassificationRoute}?unNumber=2900") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }},
                text = "Infectious Substance Affecting Animals",
                unNumber = 2900
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    // use '-' as unNumber for Category Exempt
                    navController.navigate("${HomeNavigation.ClassificationRoute}?unNumber=3373") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }},
                text = "Biological Substances Category B",
                unNumber = 3373
            )
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    // use '-' as unNumber for Category Exempt
                    navController.navigate("${HomeNavigation.ClassificationRoute}?unNumber=3291") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }},
                text = "Biomedical, Clinical or Medical Waste",
                unNumber = 3291
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(0.5f),
            horizontalArrangement = Arrangement.Center
        ) {
            SubstanceSelectionButton(
                onClick = {
                    // use '-' as unNumber for Category Exempt
                    navController.navigate("${HomeNavigation.ClassificationRoute}?unNumber=-") {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }},
                text = "Human/Animal Specimen Category Exempt"
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        ClassificationStartButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp),
            onClick = {
                navController.navigate(HomeNavigation.ClassificationRoute) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            text = "Click here if you don’t know your substance type!"
        )
    }
}



