package com.example.iss_tool

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.primary_navy_blue

@OptIn(ExperimentalComposeUiApi::class)
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
            if(category == Category.Exempt) {
                ClickableIcon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    id = R.drawable.arrow_forward,
                    description = "information"
                )
                {
                    navController.navigate(
                        "${HomeNavigation.ShippingInformationRoute}/" +
                                "${category}/" +
                                "${unNumber}/" +
                                "${unSubstance}/" +
                                "${quantity}/" +
                                "${substanceName}/"+
                                "${shippingMethod}/"+
                                "No/"
                                +null

                    ) {
                        launchSingleTop = true
                    }
                }
            }
        }
        when(category){
            Category.A -> when(shippingMethod){
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
            Category.B -> when(shippingMethod){
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
            Category.Exempt ->{
                shipment(modifier = Modifier,
                    shipmentMethod = shippingMethod,
                    title = shippingMethod,
                    info_body = "For shipments of Exempt Human or Animal Specimen Category, there are no quantity limits per package. "
                )
            }
            else -> { throw Exception("Argument exception: Category.$category is not handled by ShippingDecisionScreen.")}
        }
        var selectedOption by remember { mutableStateOf<IceOption?>(IceOption.Option2) }
        if(category == Category.A || category == Category.B) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(customShapes.large)
                    .background(blue_who.copy(alpha = 0.2f))
//                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Are you using Ice as Refrigerant?")
                OptionRadioButton(
                    option = IceOption.Option1,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                OptionRadioButton(
                    option = IceOption.Option2,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                var text by remember { mutableStateOf(TextFieldValue("")) }
                var showError2 by remember { mutableStateOf(false) }
                var assignedValue by remember { mutableStateOf<String?>(null) }
                val keyboardController = LocalSoftwareKeyboardController.current
                if (selectedOption == IceOption.Option1) {

                    // Display quantity  box when yes is selected
                    OutlinedTextFieldComponent(text="Quantity in Kg",value = text, showError = showError2, onValueChange = {
                        text = if (it.text.isDigitsOnly()) it else text
                        showError2 = false // Hide the error message when the user starts typing
                    }, onDoneAction = {
                        keyboardController?.hide()
                    }, modifier = Modifier.fillMaxWidth()
                    )
                    if (showError2) {
                        ErrorMessage("Quantity is required!", modifier = Modifier)
                    }

                }
                StartButton(onClick = {
                    if(selectedOption == IceOption.Option2){
                        navController.navigate(
                            "${HomeNavigation.ShippingInformationRoute}/" +
                                    "${category}/" +
                                    "${unNumber}/" +
                                    "${unSubstance}/" +
                                    "${quantity}/" +
                                    "${substanceName}/"+
                                    "${shippingMethod}/"+
                                    "${selectedOption?.text}/"
                                    +"${assignedValue}"

                        ) {
                            launchSingleTop = true
                        }
                    }
                    else{
                        if (text.text.isEmpty()) {
                            showError2 = true
                        } else {
                            assignedValue = text.text
                            navController.navigate(
                                "${HomeNavigation.ShippingInformationRoute}/" +
                                        "${category}/" +
                                        "${unNumber}/" +
                                        "${unSubstance}/" +
                                        "${quantity}/" +
                                        "${substanceName}/"+
                                        "${shippingMethod}/"+
                                        "${selectedOption?.text}/"
                                        +"${assignedValue}"

                            ) {
                                launchSingleTop = true
                            }
                        }
                    }

                }, text = "Proceed")

            }

            }

            }




    }
}
