package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography

object HomeNavigation {
    const val MainHomeRoute = "main"
    const val ClassificationRoute = "classification"
}

fun NavGraphBuilder.homeGraph(navController: NavHostController, paddingModifier: Modifier) {
    navigation(
        startDestination = HomeNavigation.MainHomeRoute,
        route = BottomBarScreen.Home.route
    ) {
        composable(route = HomeNavigation.MainHomeRoute) {
            HomeScreen(navController = navController, paddingModifier = paddingModifier)
        }
        composable(route = HomeNavigation.ClassificationRoute) {
            ClassificationScreen(navController = navController, paddingModifier = paddingModifier)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, paddingModifier: Modifier) {
    Column (
        modifier = paddingModifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Show_logo(modifier = paddingModifier.align(Alignment.Start), id = R.drawable.who_logo, color = blue_who)
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
                onClick = { /*TODO*/ },
                text = "Infectious Substance Affecting Humans",
                unNumber = 2814
            )
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
                text = "Biological Substances Category B",
                unNumber = 3373
            )
            SubstanceSelectionButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
                text = "Human/Animal Specimen Category Exempt"
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        ClassificationStartButton(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
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



