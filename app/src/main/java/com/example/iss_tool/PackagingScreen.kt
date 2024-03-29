package com.example.iss_tool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography

/**
 * Screen handling packaging process for Category A and B, given classification from ClassificationScreen
 * **/

@Composable
fun PackagingScreen(
    navController: NavController,
    modifier: Modifier,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance?,
    quantity: Int?,
    substanceName: String?,
    iceQuantity: Int,
    packNumber: Int?
) {

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ClickableIcon(
            modifier = Modifier.align(Alignment.End),
            id = R.drawable.arrow_forward,
            description = "Proceed",
            step = "SHIPPING"
        ) {
            when (category) {
                Category.A -> {
                    if (quantity in 51..4000) {
                        navController.navigate(
                            "${HomeNavigation.ShippingDecision}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "CargoOnly/" +
                                    "$iceQuantity"

                        ) {
                            launchSingleTop = true
                        }
                    } else if (quantity!! >= 4000 || packNumber!! * iceQuantity > 200) {
                        navController.navigate(
                            "${HomeNavigation.ShippingDecision}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "ByRoad/" +
                                    "$iceQuantity"
                        ) {
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(
                            "${HomeNavigation.ShippingRoute}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "$iceQuantity"

                        ) {
                            launchSingleTop = true
                        }
                    }
                }

                Category.B -> {
                    if (quantity!! > 1000 || packNumber!! * iceQuantity > 200) {
                        navController.navigate(
                            "${HomeNavigation.ShippingDecision}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "ByRoad/" +
                                    "$iceQuantity"
                        ) {
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(
                            "${HomeNavigation.ShippingRoute}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "$iceQuantity"

                        ) {
                            launchSingleTop = true
                        }
                    }
                }

                else -> {
                    if ((packNumber!! * iceQuantity) > 200) {
                        navController.navigate(
                            "${HomeNavigation.ShippingDecision}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "ByRoad/" +
                                    "$iceQuantity"
                        ) {
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(
                            "${HomeNavigation.ShippingRoute}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/" +
                                    "$iceQuantity"

                        ) {
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (unNumber != null) {
                Text(
                    text = "$unSubstance\nUN $unNumber\n",
                    style = customTypography.bodyLarge,
                    color = customColorScheme.primary,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .align(Alignment.CenterVertically)
                )
            } else {
                Text(
                    text = "$unSubstance\n",
                    style = customTypography.bodyLarge,
                    color = customColorScheme.primary,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(customShapes.large)
                .background(blue_who.copy(alpha = 0.2f))
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
        ) {

            Triplepackagingsystem(Modifier, category, iceQuantity > 0)

        }

    }

}
