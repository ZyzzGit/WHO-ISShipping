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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iss_tool.database.Substance
import com.example.iss_tool.database.SubstanceViewModel
import com.example.iss_tool.theme.MyCustomTheme
import com.example.iss_tool.theme.blue_who
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader

class MainActivity : ComponentActivity() {
    private lateinit var nSubstanceModel: SubstanceViewModel
    private fun ReadExcel(): List<Substance> {
        val bufferReader = BufferedReader(assets.open("substance_list.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
        )
        val list = mutableListOf<Substance>()
        csvParser.forEach {
            it?.let {
                val substance = Substance(
                    id = it.get(0).toInt(),
                    substanceName = it.get(1),
                    category = it.get(2),
                    code = it.get(3)
                )
                list.add(substance)
            }

        }
        return list
    }
    private fun insertdataToDatabase() {
        val list = ReadExcel()
        list.forEach {
            nSubstanceModel.addSubstance(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        nSubstanceModel = ViewModelProvider(this).get(SubstanceViewModel::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            insertdataToDatabase()
        }

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
        topBar = {
            TopAppBar(
                title = {
                    Show_logo(id = R.drawable.who_logo, color = blue_who)
                }
            )
        },
        bottomBar = { AppBottomBar(navController = navController) },
    ) //content:
    {paddingValues->
        BottomNavigationGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
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
    NavigationBar {
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
        selected = if (screen == BottomBarScreen.Home)
            HomeNavigation.MainHomeRoute == backStackEntry.value?.destination?.route
            else screen.route == backStackEntry.value?.destination?.route,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}