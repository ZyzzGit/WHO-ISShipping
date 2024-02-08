package com.example.iss_tool

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.primary_navy_blue

@Composable
fun LabelsMarksScreen(
    navController: NavHostController,
    modifier: Modifier,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance,
    quantity: Int?,
    substanceName: String?,  // only provided for Category A
    shippingMethod: ShippingMethod,
    shipperName: String,
    shipperAddress: String,
    receiverName: String,
    receiverAddress: String,
    responsibleName: String?,
    responsiblePhone: String?,
    iceQuantity: Int

) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ClickableIcon(
            modifier = Modifier.align(Alignment.End),
            id = R.drawable.arrow_forward,
            description = "documentation",
            step = "DOCUMENTATION"
        ) {
            navController.navigate(
                "${HomeNavigation.DocumentationRoute}/" + "${category}/" + "${unNumber}/" + "${unSubstance}/" + "${quantity}/" + "${iceQuantity}/" + "${shippingMethod}/" + "${shipperName}/" + "${shipperAddress}/" + "${receiverName}/" + "${receiverAddress}/" + "${substanceName}/" + "${responsibleName}/" + "$responsiblePhone"

            ) {
                launchSingleTop = true
            }

        }
        var shipperInfo = false
        var receiverInfo = false
        var responsibleInfo = false
        var infectiousLabel = false
        var unSpecificationMark = false
        var orientationArrows = false
        var shippingNameNumber = false
        var cargoLabel = false
        var dryIceLabel = false
        var dangerousGoodsLabel = false
        var shippingName = false
        var shippingNumber = false

        if (shippingMethod == ShippingMethod.CargoOnly) {
            cargoLabel = true
        }
        if (iceQuantity > 0) {
            dryIceLabel = true
            dangerousGoodsLabel = true
        }
        when (category) {
            Category.A -> {
                shipperInfo = true
                receiverInfo = true
                responsibleInfo = true
                infectiousLabel = true
                unSpecificationMark = true
                if (quantity!! > 50) {
                    orientationArrows = true
                }
                shippingNameNumber = true

            }

            Category.B -> {
                shipperInfo = true
                receiverInfo = true
                shippingName = true
                shippingNumber = true

            }

            Category.Exempt -> {
                shipperInfo = true
                receiverInfo = true
                shippingName = true
            }

            else -> {
                throw Exception("Argument exception: Category.$category is not handled by LabelsMarksScreen.")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "MARKS AND LABELS",
                Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                style = customTypography.bodyMedium,
                color = primary_navy_blue
            )

        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            if (shippingNameNumber) {
                InfoBox(Modifier, "$unSubstance\n UN $unNumber", "", "")
            }
            Spacer(modifier = Modifier.height(5.dp))
            if (shippingName) {
                InfoBox(Modifier, "$unSubstance", "", "")
            }
            Spacer(modifier = Modifier.height(5.dp))
            if (shipperInfo) {
                InfoBox(Modifier, "SHIPPER", shipperName, shipperAddress)
            }
            Spacer(modifier = Modifier.height(5.dp))
            if (receiverInfo) {
                InfoBox(
                    modifier = Modifier,
                    title = "RECEIVER",
                    name = receiverName,
                    address = receiverAddress
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            if (responsibleInfo) {
                InfoBox(
                    modifier = Modifier,
                    title = "EMERGENCY \n" + "CONTACT 24H/24H",
                    name = "$responsibleName",
                    address = "$responsiblePhone"
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            if (dryIceLabel) {
                InfoBox(
                    modifier = Modifier,
                    title = "DRY ICE \n" + "UN 1845",
                    name = "NET WEIGHT: $iceQuantity Kg",
                    address = ""
                )
            }


            if (infectiousLabel) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.infectious_label_a,
                    description = "Infectious Label"
                )

            }
            if (shippingNumber) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.shipping_number_b,
                    description = "Shipping Number"
                )

            }
            if (cargoLabel) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.cargo_label,
                    description = "Cargo Label"
                )
            }
            if (orientationArrows) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.orientation_arrows,
                    description = "Orientation"
                )
            }
            if (unSpecificationMark) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.un_number,
                    description = "UN specification Mark"
                )

            }
            if (dangerousGoodsLabel) {
                MarksDisplay(
                    modifier = Modifier,
                    imageId = R.drawable.dangerous_goods,
                    description = "Dangerous label"
                )
            }
        }
    }
}

