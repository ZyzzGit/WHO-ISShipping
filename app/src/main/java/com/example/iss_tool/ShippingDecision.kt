package com.example.iss_tool


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography



@Composable
fun ShippingDecision(
    navController: NavController,
    modifier: Modifier,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance,
    quantity: Int?,
    substanceName: String?,
    shippingMethod: String,
    iceQuantity: Int
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
            description = "information",
            step = "MARKS AND LABELS"
        )
        {
            navController.navigate(
                "${HomeNavigation.ShippingInformationRoute}/" +
                        "${category}/" +
                        "${unNumber}/" +
                        "${unSubstance}/" +
                        "${quantity}/" +
                        "${substanceName}/" +
                        "${shippingMethod}/" +
                        "$iceQuantity"

            ) {
                launchSingleTop = true
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
                )
            } else {
                Text(
                    text = "$unSubstance",
                    style = customTypography.bodyLarge,
                    color = customColorScheme.primary,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
        when (category) {
            Category.A -> when (shippingMethod) {
                "CargoOnly" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Cargo Aircraft Only",
                        infoBody = "For shipments being carried on a cargo only aircraft, no more than 4L or 4kg of Category A infectious substance per package is allowed."
                    )
                }

                "Passenger" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Passenger Aircraft",
                        infoBody = "For shipments being carried in the cargo hold of passenger aircraft, no more than 50mL or 50g of Category A infectious substance per package is allowed."
                    )
                }

                "ByRoad" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Road",
                        infoBody = "For shipments being carried via surface transport (road, rail or maritime), there are no quantity limits per package. "
                    )
                }
            }

            Category.B -> when (shippingMethod) {
                "CargoOnly" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Cargo Aircraft Only",
                        infoBody = "For shipments being carried by air (passenger or cargo aircraft), the primary inner receptacle must not contain more than 1L and the outer packaging must not contain more than 4L of material. This excludes any quantity of coolants used, such as dry ice or liquid nitrogen. \n"
                    )
                }

                "Passenger" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Passenger Aircraft",
                        infoBody = "For shipments being carried by air (passenger or cargo aircraft), the primary inner receptacle must not contain more than 1L and the outer packaging must not contain more than 4L of material. This excludes any quantity of coolants used, such as dry ice or liquid nitrogen. \n"
                    )
                }

                "ByRoad" -> {
                    Shipment(
                        modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Road",
                        infoBody = "For shipments being carried via surface transport (road, rail or maritime), there are no quantity limits per package. "
                    )
                }
            }

            Category.Exempt -> {
                Shipment(
                    modifier = Modifier,
                    shipmentMethod = shippingMethod,
                    title = shippingMethod,
                    infoBody = "For shipments of Exempt Human or Animal Specimen Category, there are no quantity limits per package. "
                )
            }

            else -> {
                throw Exception("Argument exception: Category.$category is not handled by ShippingDecisionScreen.")
            }
        }
        if (iceQuantity != 0) {
            NoteText(
                modifier = modifier,
                id = R.drawable.info_icon,
                iconInfo = "info",
                textInfo = "The total quantity of Dry Ice is limited to 200 Kg per aircraft."
            )
        }
    }
}

