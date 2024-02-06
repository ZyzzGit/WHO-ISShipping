package com.example.iss_tool

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.orange_who

@Composable
fun ShippingScreen(
    navController: NavController,
    modifier: Modifier,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance,
    quantity: Int?,
    substanceName:String?,
    iceQuantity:Int
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
        when(category){
            Category.A ->{
                noteText(modifier = Modifier,
                    id=R.drawable.info_icon,
                    icon_info = "info",
                    textInfo ="For shipments being carried in the cargo hold of passenger aircraft, no more than 50mL or 50g of Category A infectious substance per package is allowed. \n" +
                            "For shipments being carried on a cargo only aircraft, no more than 4L or 4kg of Category A infectious substance per package is allowed.")
            }
            Category.B -> {
                noteText(modifier = Modifier,
                    id=R.drawable.info_icon,
                    icon_info = "info",
                    textInfo ="For shipments being carried by air (passenger or cargo aircraft), the primary inner receptacle must not contain more than 1L and the outer packaging must not contain more than 4L of material. This excludes any quantity of coolants used, such as dry ice or liquid nitrogen. \n" +
                            "For shipments being carried via surface transport (road, rail or maritime), there are no quantity limits per package."
                )
            }
            Category.Exempt -> {}
            else -> { throw Exception("Argument exception: Category.$category is not handled by ShippingScreen.") }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Select your shipping method",
            style= customTypography.bodyMedium,
            modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth().requiredHeight(120.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 22.dp,
                alignment = Alignment.CenterHorizontally
            )
        )
        {
            FloatingActionButton(
                modifier = Modifier.weight(.1f).border(1.dp,black, shape = customShapes.large),
                onClick = {

                    navController.navigate(
                        "${HomeNavigation.ShippingDecision}/" +
                                "${category}/" +
                                "${unNumber}/" +
                                "${unSubstance}/" +
                                "${quantity}/" +
                                "${substanceName}/" +
                                "Passenger/"+
                                "$iceQuantity"

                    ) {
                        launchSingleTop = true
                    }
                },
                shape = customShapes.large,
                containerColor = customColorScheme.background
            ) {
                // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(customShapes.large)
                        .background(customColorScheme.background), contentAlignment = Alignment.Center
                ) {
                   Text( "PASSENGER\n"+"AIRCRAFT", style = customTypography.bodyMedium, color = orange_who,textAlign = TextAlign.Center)
                }
            }
            FloatingActionButton(
                modifier = Modifier.weight(.1f).border(1.dp,black, shape = customShapes.large),
                onClick = {
                    navController.navigate(
                        "${HomeNavigation.ShippingDecision}/" +
                                "${category}/" +
                                "${unNumber}/" +
                                "${unSubstance}/" +
                                "${quantity}/" +
                                "${substanceName}/" +
                                "CargoOnly/"+
                                "$iceQuantity"

                    ) {
                        launchSingleTop = true
                    }
                },
                shape = customShapes.large,
                containerColor = customColorScheme.background
            ) {
                // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(customShapes.large)
                        .background(customColorScheme.background), contentAlignment = Alignment.Center
                ) {
                    Text( "CARGO \n" +
                            "AIRCRAFT \n" +
                            "ONLY", style = customTypography.bodyMedium, color = orange_who, textAlign = TextAlign.Center)
                }
            }
            FloatingActionButton(
                modifier = Modifier.weight(.1f).border(1.dp,black, shape = customShapes.large),
                onClick = {
                    navController.navigate(
                        "${HomeNavigation.ShippingDecision}/" +
                                "${category}/" +
                                "${unNumber}/" +
                                "${unSubstance}/" +
                                "${quantity}/" +
                                "${substanceName}/" +
                                "ByRoad/"+
                                "$iceQuantity"

                    ) {
                        launchSingleTop = true
                    }
                },
                shape = customShapes.large,
                containerColor = customColorScheme.background
            ) {
                // Encapsulating box is used only to obtain a fully white background, otherwise there is a gray tint
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(customShapes.large)
                        .background(customColorScheme.background), contentAlignment = Alignment.Center
                ) {
                    Text( "ROAD", style = customTypography.bodyMedium, color = orange_who, textAlign = TextAlign.Center)
                }
            }


        }
    }
}


