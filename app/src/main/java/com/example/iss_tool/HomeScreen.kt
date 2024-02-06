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
    const val PackagingRoute = "packaging"
    const val ShippingRoute = "shipping"
    const val ShippingDecision = "shipping decision"
    const val ShippingInformationRoute = "shippingInfo"
    const val MarkingRoute = "markingroute"
    const val DocumentationRoute = "documentation"
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
        composable(
          route = "${HomeNavigation.PackagingRoute}/{category}/{unNumber}/{unSubstance}/{quantity}/{substanceName}/{iceQuantity}",
          arguments = listOf(
              navArgument("category") { type = NavType.StringType },
              navArgument("unNumber") { type = NavType.StringType; nullable = true  },
//              navArgument("unNumber") { nullable = true },
              navArgument("unSubstance") { type = NavType.StringType },
//              navArgument("quantity") { type = NavType.IntType },
              navArgument("quantity") { type = NavType.StringType; nullable = true  },
              navArgument("substanceName") { type = NavType.StringType;nullable = true },
              navArgument("iceQuantity") { type = NavType.IntType }
          )
        ) {navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            PackagingScreen(
                navController = navController,
                modifier = modifier,
                category = category!!,
                unNumber = unNumber,
                unSubstance = unSubstance,
                quantity = quantity,
                substanceName=substanceName,
                iceQuantity =  iceQuantity!!
            )
        }
        composable(
            route = "${HomeNavigation.ShippingRoute}/{category}/{unNumber}/{unSubstance}/{quantity}/{substanceName}/{iceQuantity}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("unNumber") { type = NavType.StringType;nullable = true },
                navArgument("unSubstance") { type = NavType.StringType },
                navArgument("quantity") { type = NavType.StringType;nullable = true },
                navArgument("substanceName") { type = NavType.StringType;nullable = true },
                navArgument("iceQuantity") { type = NavType.IntType  }
            )
        ) {navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            ShippingScreen(
                navController = navController,
                modifier = modifier,
                category = category!!,
                unNumber = unNumber,
                unSubstance = unSubstance!!,
                quantity = quantity,
                substanceName = substanceName,
                iceQuantity = iceQuantity!!
            )
        }
        composable(
            route = "${HomeNavigation.ShippingDecision}/{category}/{unNumber}/{unSubstance}/{quantity}/{substanceName}/{shippingMethod}/{iceQuantity}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("unNumber") { type = NavType.StringType;nullable = true },
                navArgument("unSubstance") { type = NavType.StringType },
                navArgument("quantity") { type = NavType.StringType;nullable = true },
                navArgument("substanceName") { type = NavType.StringType;nullable = true },
                navArgument("shippingMethod"){type = NavType.StringType} ,
                navArgument("iceQuantity") { type = NavType.IntType }
            )
        ) {navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val shippingMethod = navBackStackEntry.arguments?.getString("shippingMethod")
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            ShippingDecision(
                navController = navController,
                modifier = modifier,
                category = category!!,
                unNumber = unNumber,
                unSubstance = unSubstance!!,
                quantity = quantity,
                substanceName = substanceName,
                shippingMethod = shippingMethod!!,
                iceQuantity = iceQuantity!!
            )
        }
        composable(
            route = "${HomeNavigation.ShippingInformationRoute}/{category}/{unNumber}/{unSubstance}/{quantity}/{substanceName}/{shippingMethod}/{iceQuantity}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("unNumber") { type = NavType.StringType;nullable = true },
                navArgument("unSubstance") { type = NavType.StringType },
                navArgument("quantity") { type = NavType.StringType;nullable = true },
                navArgument("substanceName") { type = NavType.StringType;nullable = true },
                navArgument("shippingMethod"){type = NavType.StringType},
                navArgument("iceQuantity") { type = NavType.IntType },
            )
        ) {navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val shippingMethod = navBackStackEntry.arguments?.getString("shippingMethod")?.let { ShippingMethod.fromString(it) }
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            ShippingInformationScreen(
                navController = navController,
                modifier = modifier,
                category = category!!,
                unNumber = unNumber,
                unSubstance = unSubstance!!,
                quantity = quantity,
                substanceName = substanceName,
                shippingMethod = shippingMethod!!,
                iceQuantity = iceQuantity!!
            )
        }
        composable(
            route = "${HomeNavigation.MarkingRoute}/{category}/{unNumber}/{unSubstance}/{quantity}/{substanceName}/{shippingMethod}/{shipperName}/{shipperAddress}/{receiverName}/{receiverAddress}/{responsibleName}/{responsiblePhone}/{iceQuantity}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("unNumber") { type = NavType.StringType;nullable = true },
                navArgument("unSubstance") { type = NavType.StringType },
                navArgument("quantity") { type = NavType.StringType;nullable = true },
                navArgument("substanceName") { type = NavType.StringType;nullable = true },
                navArgument("shippingMethod") { type = NavType.StringType },
                navArgument("shipperName") { type = NavType.StringType },
                navArgument("shipperAddress") { type = NavType.StringType },
                navArgument("receiverName") { type = NavType.StringType },
                navArgument("receiverAddress") { type = NavType.StringType },
                navArgument("responsibleName") { type = NavType.StringType; nullable = true},
                navArgument("responsiblePhone") { type = NavType.StringType; nullable = true } ,
                navArgument("iceQuantity") { type = NavType.IntType }
            )
        ){navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val shippingMethod = navBackStackEntry.arguments?.getString("shippingMethod")?.let { ShippingMethod.fromString(it) }
            val shipperName = navBackStackEntry.arguments?.getString("shipperName")
            val shipperAddress = navBackStackEntry.arguments?.getString("shipperAddress")
            val receiverName = navBackStackEntry.arguments?.getString("receiverName")
            val receiverAddress = navBackStackEntry.arguments?.getString("receiverAddress")
            val responsibleName = navBackStackEntry.arguments?.getString("responsibleName")
            val responsiblePhone = navBackStackEntry.arguments?.getString("responsiblePhone")
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            LabelsMarksScreen(
                navController = navController,
                modifier = modifier,
                category =category!!,
                unNumber =unNumber,
                unSubstance =unSubstance!!,
                quantity =quantity,
                substanceName =substanceName,
                shippingMethod =shippingMethod!!,
                shipperName =shipperName!!,
                shipperAddress =shipperAddress!!,
                receiverName =receiverName!!,
                receiverAddress =receiverAddress!!,
                responsibleName =responsibleName,
                responsiblePhone =responsiblePhone,
                iceQuantity = iceQuantity!!
            )
        }
        composable(
          route = "${HomeNavigation.DocumentationRoute}/{category}/{unNumber}/{unSubstance}/{quantity}/{iceQuantity}/{shippingMethod}/{shipperName}/{shipperAddress}/" +
                  "{receiverName}/{receiverAddress}/{substanceName}/{responsibleName}/{responsiblePhone}",
          arguments = listOf(
              navArgument("category") { type = NavType.StringType },
              navArgument("unNumber") { type = NavType.StringType; nullable = true },
              navArgument("unSubstance") { type = NavType.StringType },
              navArgument("quantity") { type = NavType.StringType;nullable = true },
              navArgument("iceQuantity") { type = NavType.IntType },
              navArgument("shippingMethod") { type = NavType.StringType },
              navArgument("shipperName") { type = NavType.StringType },
              navArgument("shipperAddress") { type = NavType.StringType },
              navArgument("receiverName") { type = NavType.StringType },
              navArgument("receiverAddress") { type = NavType.StringType },
              navArgument("substanceName") { nullable = true },
              navArgument("responsibleName") { type = NavType.StringType; nullable = true},
              navArgument("responsiblePhone") { type = NavType.StringType; nullable = true } ,
          )
        ) {navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")?.let { Category.fromString(it) }
            val unNumber = navBackStackEntry.arguments?.getString("unNumber")?.toIntOrNull()
            val unSubstance = navBackStackEntry.arguments?.getString("unSubstance")?.let { UnSubstance.fromString(it) }
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull()
            val iceQuantity = navBackStackEntry.arguments?.getInt("iceQuantity")
            val shippingMethod = navBackStackEntry.arguments?.getString("shippingMethod")?.let { ShippingMethod.fromString(it) }
            val shipperName = navBackStackEntry.arguments?.getString("shipperName")
            val shipperAddress = navBackStackEntry.arguments?.getString("shipperAddress")
            val receiverName = navBackStackEntry.arguments?.getString("receiverName")
            val substanceName = navBackStackEntry.arguments?.getString("substanceName")
            val responsibleName = navBackStackEntry.arguments?.getString("responsibleName")
            val responsiblePhone = navBackStackEntry.arguments?.getString("responsiblePhone")
            val receiverAddress = navBackStackEntry.arguments?.getString("receiverAddress")
            DocumentationScreen(
                navController = navController,
                modifier = modifier,
                category = category!!,
                unNumber = unNumber,
                unSubstance = unSubstance!!,
                quantity = quantity,
                iceQuantity = iceQuantity!!,
                shippingMethod = shippingMethod!!,
                shipperName = shipperName!!,
                shipperAddress = shipperAddress!!,
                receiverName = receiverName!!,
                receiverAddress = receiverAddress!!,
                substanceName = substanceName,
                responsibleName = responsibleName,
                responsiblePhone = responsiblePhone
            )
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
        // TODO: REMOVE DEBUGGING CODE BELOW
        /*navController.navigate(
            HomeNavigation.DocumentationRoute +
                    "/${Category.A}/" +
                    "${2814}/" +
                    "${UnSubstance.ISHumans}/" +
                    "${42}/" +
                    "${17}/" +
                    "${ShippingMethod.Passenger}/" +
                    "Nico/" +
                    "Examplestreet 7/" +
                    "WHO Italy/" +
                    "Via something 93/" +
                    "Kyasanur Forest disease virus/" +
                    "Leonardo da Vinci/" +
                    "+39 884794294"
        ) {
            launchSingleTop = true
        }*/
    }
}



