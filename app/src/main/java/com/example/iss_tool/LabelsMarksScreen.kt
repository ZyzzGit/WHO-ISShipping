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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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

    ){
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var shipper_info : Boolean = false
        var receiver_info : Boolean =  false
        var responsible_info : Boolean = false
        var infectious_label : Boolean = false
        var un_specification_mark : Boolean = false
        var orientation_arrows : Boolean = false
        var shipping_name_number:Boolean = false
        var cargo_label : Boolean = false
//        TODO when ice added
        var dry_ice_label:Boolean = false
        var dangerous_goods : Boolean = false

        var shipping_name:Boolean = false
        var shipping_number:Boolean = false

        if(iceQuantity > 0){
            dry_ice_label = true
            dangerous_goods = true
        }
        when(category){
            Category.A->
            {
                shipper_info = true
                receiver_info = true
                responsible_info = true
                infectious_label = true
                un_specification_mark = true
                if(quantity!! > 50){
                    orientation_arrows = true
                }
                shipping_name_number=true
                if(shippingMethod == ShippingMethod.CargoOnly){
                    cargo_label = true
                }
            }
            Category.B->{
                shipper_info = true
                receiver_info = true
                shipping_name = true
                shipping_number = true

            }
            Category.Exempt->{
                shipper_info = true
                receiver_info = true
                shipping_name = true
            }
            else -> { throw Exception("Argument exception: Category.$category is not handled by LabelsMarksScreen.")}
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("MARKS AND LABELS",
                Modifier.weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
                style= customTypography.bodyMedium,
                color = primary_navy_blue)

                ClickableIcon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    id = R.drawable.arrow_forward,
                    description = "documentation"
                )
                {
                    navController.navigate(
                        "${HomeNavigation.DocumentationRoute}/" +
                                "${category}/" +
                                "${unNumber}/" +
                                "${unSubstance}/" +
                                "${quantity}/" +
                                "${iceQuantity}/" +
                                "${shippingMethod}/" +
                                "${shipperName}/" +
                                "${shipperAddress}/" +
                                "${receiverName}/" +
                                "${receiverAddress}/" +
                                "${substanceName}/" +
                                "${responsibleName}/" +
                                "$responsiblePhone"

                    ) {
                        launchSingleTop = true
                    }

            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        if(shipping_name_number){
            InfoBox(Modifier,"$unSubstance\n UN $unNumber","","")
        }
        Spacer(modifier = Modifier.height(5.dp))
        if(shipping_name){
            InfoBox(Modifier,"$unSubstance","","")
        }
        Spacer(modifier = Modifier.height(5.dp))
    if(shipper_info){
        InfoBox(Modifier,"SHIPPER",shipperName,shipperAddress)
    }
        Spacer(modifier = Modifier.height(5.dp))
    if(receiver_info){
        InfoBox(modifier = Modifier, title = "RECEIVER", name = receiverName, address = receiverAddress)
    }
        Spacer(modifier = Modifier.height(5.dp))
    if(responsible_info){
        InfoBox(modifier = Modifier, title = "EMERGENCY \n" +
                "CONTACT 24H/24H", name = "$responsibleName", address ="$responsiblePhone" )
    }
        Spacer(modifier = Modifier.height(5.dp))
        if(dry_ice_label){
            InfoBox(modifier = Modifier, title = "DRY ICE \n" +
                    "UN 1845", name = "NET WEIGHT: $iceQuantity Kg", address ="" )
    }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (infectious_label)
            { item {

                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "Infectious Label",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
            if (shipping_number) {
                item {

                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "Shipping Number",
                        modifier = Modifier
                            .padding(8.dp)
//                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.large)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
            if (cargo_label) {
                item {

                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "Cargo Label",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.large)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
            if (orientation_arrows) {
            item {
                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "Orientation",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.large)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
            if (un_specification_mark) {
            item {

                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "UN specification Mark",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.large)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
            if (dangerous_goods) {
            item {
                    Image(
                        painter = painterResource(R.drawable.infectious_label_a),
                        contentDescription = "UN specification Mark",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(shape = MaterialTheme.shapes.large)
//                            .clickable {
//                                // Handle image click if needed
//                                println("Infectious Label clicked")
//                            }
                    )
                }
            }
        }
        StartButton(onClick = {
            navController.navigate(
                "${HomeNavigation.DocumentationRoute}/" +
                        "${category}/" +
                        "${unNumber}/" +
                        "${unSubstance}/" +
                        "$quantity/" +
                        "${iceQuantity}/" +
                        "${shippingMethod}//" +
                        "${shipperName}/" +
                        "${shipperAddress}/" +
                        "${receiverName}/" +
                        "${receiverAddress}/" +
                        "${substanceName}/" +
                        "${responsibleName}/" +
                        "$responsiblePhone"
            ) {
                launchSingleTop = true
            }},
            text = "To documentation"
        )
    }
}