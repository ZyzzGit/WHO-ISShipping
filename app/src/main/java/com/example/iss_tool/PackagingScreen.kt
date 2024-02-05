package com.example.iss_tool

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PackagingScreen(
    navController: NavController,
    modifier: Modifier,
    category: String,
    unNumber: Int?,
    unSubstance: String?,
    quantity: Int?,
    substanceName:String?
) {

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
            if(unNumber != null){
            Text(
                text = "$unSubstance\nUN $unNumber\n",
                style = customTypography.bodyLarge,
                color = customColorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .align(Alignment.CenterVertically)
            )}
            else{
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

            ClickableIcon(modifier = Modifier.align(Alignment.CenterVertically),id = R.drawable.arrow_forward, description = "Proceed") {
               when(category){
                   "Category A" -> {
                       if(quantity in 51..4000){
                           navController.navigate(
                               "${HomeNavigation.ShippingDecision}/" +
                                       "${category}/" +
                                       "${unNumber}/" +
                                       "${unSubstance}/" +
                                       "${quantity}/"+
                                       "${substanceName}/"+
                                       "CargoOnly"
                           ) {
                               launchSingleTop = true
                           }
                       }
                       else if (quantity!! >= 4000){
                           navController.navigate(
                               "${HomeNavigation.ShippingDecision}/" +
                                       "${category}/" +
                                       "${unNumber}/" +
                                       "${unSubstance}/" +
                                       "${quantity}/"+
                                       "${substanceName}/"+
                                       "ByRoad"
                           ) {
                               launchSingleTop = true
                           }
                       }
                       else {
                           navController.navigate(
                               "${HomeNavigation.ShippingRoute}/" +
                                       "${category}/" +
                                       "${unNumber}/" +
                                       "${unSubstance}/" +
                                       "${quantity}/"+
                                       "${substanceName}"

                           ) {
                               launchSingleTop = true
                           }
                       }
                   }
                   "Category B" -> {
                       if(quantity!! > 1000){
                           navController.navigate(
                               "${HomeNavigation.ShippingDecision}/" +
                                       "${category}/" +
                                       "${unNumber}/" +
                                       "${unSubstance}/" +
                                       "${quantity}/"+
                                       "${substanceName}/"+
                                       "ByRoad"
                           ) {
                               launchSingleTop = true
                           }
                       }
                       else{
                       navController.navigate(
                           "${HomeNavigation.ShippingRoute}/" +
                                   "${category}/" +
                                   "${unNumber}/" +
                                   "${unSubstance}/" +
                                   "${quantity}/"+
                                   "${substanceName}"

                       ) {
                           launchSingleTop = true
                       }}
                   }
                   else -> {
                       navController.navigate(
                           "${HomeNavigation.ShippingRoute}/" +
                                   "${category}/" +
                                   "${unNumber}/" +
                                   "${unSubstance}/" +
                                   "${quantity}/"+
                                   "${substanceName}"

                       ) {
                           launchSingleTop = true
                       }
                   }
               }
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
                when(category){
                    //Category A packaging
                    "Category A"->{
                        packagingDisplay(
                            modifier = Modifier,
                            pageCount = 2,
                            additional_text =
                            "1. Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), as well astemperatures in the range of -40°C to +55°C. \n" +
                                    "2. When the shipment is being carried at ambient temperature (or above), the primary receptacle must be glass, metal or plastic. Positive means of ensuring a leakproof seal should be provided e.g. a heat seal, skirted stopper, or metal crimp seal. If screw caps are used, they must be secured by positive means e.g. paraffin sealing tape, tape, or manufactured locking closure.\n" +
                                    "3. Lyophilized substances may also be transported in primary receptacles that are flame\u0002sealed glass ampoules or rubber-stopped glass vials fitted with metal seals."+
                                    "4. As already stated above, either the primary receptacle or this secondary container must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), and temperatures in the range of -40°C to +55°C."+
                                    "5. Must be rigid.\n" +
                                    "6. The smallest external dimension shall be not less than 100 mm.\n" +
                                    "7. An itemized list of contents shall be enclosed between the secondary container and outer packaging, including the proper shipping name and technical name in parentheses of the biological agent present in the infectious substance. When the infectious substance to be transported are unknown, but suspected of meeting the criteria for inclusion in Category A, the words “suspected Category A infectious substance” must be shown in parentheses following the proper shipping name."
                            ,)
                    }
                    //Category B packaging
                    "Category B"->{
                        packagingDisplay(
                            modifier = Modifier,
                            pageCount = 2,
                            additional_text =
                            "1. For surface transport, either the secondary or outer packaging must be rigid (i.e. the secondary packaging must be rigid, if the outer packaging is soft OR the outer packaging must be rigid, if the secondary container is soft). The latter is the most commonly applied arrangement, as a rigid outer packaging is always required for air transport.  \n" +
                                    "2. The complete triple package must be capable of passing a 1.2m ‘drop test’, to prove that is of an appropriate strength and quality. \n" +
                                    "3. The primary receptacle OR the secondary packaging must be capable of withstanding internal pressure of 95kPa (0.95 bar). This must be tested using an appropriate methodology, which is based on the receptacle or packaging type being used: for example, internal hydraulic or pneumatic pressure gauges, or external vacuum testing.")

                    }
                    //Category Exempt Human or Animal Specimen packaging
                    "Exempt Human or Animal Specimen" ->{
                        packagingDisplay(
                            modifier = Modifier,
                            pageCount = 1
                        )
                    }

                }


            }

        }

    }
