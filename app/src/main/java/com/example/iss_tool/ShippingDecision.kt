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
    category: String,
    unNumber: Int,
    unSubstance: String,
    quantity: Int,
    shippingMethod:String,
){
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (unNumber != 0) {
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
        when(category){
            "Category A" -> when(shippingMethod){
                "CargoOnly" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Cargo Aircraft Only",
                        info_body = "For shipments being carried on a cargo only aircraft, no more than 4L or 4kg of Category A infectious substance per package is allowed." )
                            }
                "Passenger" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Passenger Aircraft",
                        info_body = "For shipments being carried in the cargo hold of passenger aircraft, no more than 50mL or 50g of Category A infectious substance per package is allowed."
                    )
                }
                "ByRoad" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Road",
                        info_body = "For shipments being carried via surface transport (road, rail or maritime), there are no quantity limits per package. "
                    )
                }
            }
            "Category B" -> when(shippingMethod){
                "CargoOnly" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Cargo Aircraft Only",
                        info_body = "For shipments being carried by air (passenger or cargo aircraft), the primary inner receptacle must not contain more than 1L and the outer packaging must not contain more than 4L of material. This excludes any quantity of coolants used, such as dry ice or liquid nitrogen. \n"
                    )
                }
                "Passenger" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Passenger Aircraft",
                        info_body = "For shipments being carried by air (passenger or cargo aircraft), the primary inner receptacle must not contain more than 1L and the outer packaging must not contain more than 4L of material. This excludes any quantity of coolants used, such as dry ice or liquid nitrogen. \n"
                    )
                }
                "ByRoad" -> {
                    shipment(modifier = Modifier,
                        shipmentMethod = shippingMethod,
                        title = "Road",
                        info_body = "For shipments being carried via surface transport (road, rail or maritime), there are no quantity limits per package. "
                    )
                }
            }
            "Exempt Human or Animal Specimen" ->{
                shipment(modifier = Modifier,
                    shipmentMethod = shippingMethod,
                    title = shippingMethod,
                    info_body = "For shipments of Exempt Human or Animal Specimen category, there are no quantity limits per package."
                )
            }
        }
    }
}