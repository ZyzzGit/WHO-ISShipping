package com.example.iss_tool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iss_tool.theme.MyCustomTheme
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.primary_navy_blue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCustomTheme{
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen {
                            navController.navigate("main")
                        }
                    }
                    composable("main") {
                        MainScreen()
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomBar(navController = navController) },
    ) //content:
    {paddingValues->
        BottomNavigationGraph(
            navController = navController,
            paddingModifier = Modifier.padding(paddingValues)
        )
    }
}
@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Info,
        BottomBarScreen.Settings
    )
    NavigationBar() {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController
            )
        }
    }
}
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBarItem(
        label = {
            Text(text = screen.label)
        },
        icon = {
            Icon(
                painter = painterResource(id =screen.icon),
                contentDescription = screen.route + " icon"
            )
        },
        selected = screen.route == backStackEntry.value?.destination?.route,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}