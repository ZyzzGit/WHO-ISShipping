package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.customTypography

/**
 * Screen handling whole shipping process for Category A and B, given classification from ClassificationScreen
 * **/
@Composable
fun ShippingScreen(
    navController: NavController,
    modifier: Modifier,
    category: String,
    unNumber: Int,
    unSubstance: String,
    quantity: Int,
    substanceName: String?  // only provided for Category A
) {
    Column (
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TEMPORARY: testing all arguments are correctly passed
        Text(text = "Shipping", style = customTypography.bodyLarge)
        Text(
            text = "cat: $category\nunNum: $unNumber\nunSub: $unSubstance\nquantity: $quantity\nname: $substanceName",
            style = customTypography.bodySmall
        )
    }
}