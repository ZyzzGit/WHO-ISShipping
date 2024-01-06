package com.example.iss_tool

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.white

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: Int
) {
    object Home: BottomBarScreen(
        route = "home",
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
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        homeGraph(navController, paddingModifier)
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

@Composable
fun SubstanceSelectionButton(
    modifier: Modifier = Modifier,
    text: String,
    unNumber: Int? = null,  // should be null only for Category Exempt
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.background
    ) {
        // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(customShapes.large)
                .background(customColorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = text,
                    style = customTypography.bodySmall,
                    color = customColorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .width(126.dp)
                        .clip(customShapes.medium)
                        .background(customColorScheme.primary.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (unNumber != null) "UN ${unNumber.toString()}" else "-",
                        style = customTypography.bodySmall.copy(fontSize = 15.sp),
                        color = customColorScheme.primary,
                        modifier = Modifier.padding(5.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ClassificationStartButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.primary
    ) {
        Row(
            modifier = Modifier.padding(11.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 11.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.info_icon),
                contentDescription = "Info icon"
            )
            Text(
                modifier = Modifier.width(195.dp), /*TODO: remove hardcoding of width*/
                text = text,
                style = customTypography.bodySmall.copy(fontSize = 15.sp),
                color = white,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "Arrow forward icon"
            )
        }
    }
}

@Composable
fun BoxedFAB(
    modifier: Modifier = Modifier,
    iconId: Int,
    iconLabel: String? = null,   // optional label over icon
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = customShapes.large,
        containerColor = customColorScheme.background
    ) {
        // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(customShapes.large)
                .background(customColorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(71.dp)
                    .clip(customShapes.medium)
                    .background(customColorScheme.primary.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (iconLabel != null) {
                        Text(
                            text = iconLabel,
                            style = customTypography.bodySmall,
                            color = customColorScheme.primary
                        )
                    }
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = LocalContext.current.resources.getResourceName(iconId)
                    )
                }
            }
        }
    }
}
