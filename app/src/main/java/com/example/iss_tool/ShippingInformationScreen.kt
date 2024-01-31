package com.example.iss_tool

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customTypography

@Composable
fun ShippingInformationScreen(
    navController: NavController,
    modifier: Modifier,
    category: String,
    unNumber: Int,
    unSubstance: String,
    quantity: Int,
    substanceName: String?  // only provided for Category A
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var shipperName by remember { mutableStateOf("") }
        var shipperAddress by remember { mutableStateOf("") }
        var receiverName by remember { mutableStateOf("") }
        var receiverAddress by remember { mutableStateOf("") }
        var responsibleName by remember { mutableStateOf("") }
        var responsiblePhone by remember { mutableStateOf("") }

        var invalidSubmit by remember { mutableStateOf(false) }

        Text(text = "Please enter the following information", style = customTypography.bodyMedium)

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(6.dp),
            text = "Shipper",
            style = customTypography.bodySmall.copy(fontSize = 13.sp),
            color = black
        )
        CustomOutlinedTextField(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(0.8F),
            value = shipperName,
            onValueChange = { shipperName = it },
            isError = invalidSubmit && shipperName == "",
            label = { Text(text = "Name") }
        )
        CustomOutlinedTextField(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(0.8F),
            value = shipperAddress,
            onValueChange = { shipperAddress = it },
            isError = invalidSubmit && shipperAddress == "",
            label = { Text(text = "Address") }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(6.dp),
            text = "Receiver",
            style = customTypography.bodySmall.copy(fontSize = 13.sp),
            color = black
        )
        CustomOutlinedTextField(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(0.8F),
            value = receiverName,
            onValueChange = { receiverName = it },
            isError = invalidSubmit && receiverName == "",
            label = { Text(text = "Name") }
        )
        CustomOutlinedTextField(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(0.8F),
            value = receiverAddress,
            onValueChange = { receiverAddress = it },
            isError = invalidSubmit && receiverAddress == "",
            label = { Text(text = "Address") }
        )

        if (category == "Category A") {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Responsible",
                style = customTypography.bodySmall.copy(fontSize = 13.sp),
                color = black
            )
            CustomOutlinedTextField(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.8F),
                value = responsibleName,
                onValueChange = { responsibleName = it },
                isError = invalidSubmit && responsibleName == "",
                label = { Text(text = "Name") }
            )
            CustomOutlinedTextField(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.8F),
                value = responsiblePhone,
                onValueChange = { responsiblePhone = it },
                isError = invalidSubmit && responsiblePhone == "",
                label = { Text(text = "Phone") }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        StartButton(
            onClick = {
                if (
                    shipperName != "" && shipperAddress != "" &&
                    receiverName != "" && receiverAddress != "" &&
                    (category != "Category A" || (responsibleName != "" && responsiblePhone != ""))
                ) {
                    // TODO: Navigate to next screen
                } else {
                    invalidSubmit = true
                }
            },
            text = "Submit"
        )
    }
}
