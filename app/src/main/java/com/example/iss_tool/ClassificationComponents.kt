package com.example.iss_tool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormDisplay(
    navController: NavController,
    leaf: ClassificationLeaf,
    substanceList: List<String>,
    tableList: List<List<String>>,
    modifier: Modifier = Modifier,
    onDoneAction: () -> Unit,
) {

    // State for substance selection
    var assignedSubstance by remember { mutableStateOf<String?>(null) }
    var substanceError by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedSubstance by remember { mutableStateOf(substanceList[0]) }

    // State for quantity input
    var quantityText by remember { mutableStateOf(TextFieldValue("")) }
    var assignedQuantity by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf(false) }

    // State for ice input
    var iceQuantityText by remember { mutableStateOf(TextFieldValue("")) }
    var iceQuantityError by remember { mutableStateOf(false) }
    var assignedIceQuantity by remember { mutableStateOf<String?>(null) }
    var selectedIceOption by remember { mutableStateOf<IceOption?>(IceOption.No) }

    //State for number of packages input
    var numPackages by remember { mutableStateOf(TextFieldValue("")) }
    var numPackagesError by remember { mutableStateOf(false) }
    var assignedNumPackages by remember { mutableStateOf<String?>(null) }


    val keyboardController = LocalSoftwareKeyboardController.current

    if (leaf.category == Category.A || leaf.category == Category.B) {
        if (leaf.category == Category.A) {
            Spacer(modifier = Modifier.height(24.dp))
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                expanded = !expanded
            }) {
                OutlinedTextField(modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                    readOnly = true,
                    value = selectedSubstance,
                    onValueChange = { },
                    label = {
                        Text(
                            "Choose your substance to be shipped",
                            style = customTypography.bodyMedium
                        )
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) })
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    substanceList.drop(1).forEach { selectedSubstance_ ->
                        DropdownMenuItem(
                            text = { Text(selectedSubstance_) },
                            onClick = {
                                selectedSubstance = selectedSubstance_
                                expanded = false
                                substanceError = false

                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }

            }
            if (substanceError) {
                ErrorMessage(message = "Substance is required")
            }
        }

    }
    if (leaf.category != Category.Exempt) {
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextFieldComponent(
            text = "Quantity in mL or g",
            value = quantityText,
            showError = quantityError,
            onValueChange = {
                quantityText = if (it.text.isDigitsOnly()) it else quantityText
                quantityError = false // Hide the error message when the user starts typing
            },
            onDoneAction = {
                onDoneAction
            },
            modifier = modifier.fillMaxWidth()
        )

        if (quantityError) {
            ErrorMessage("Quantity is required!", modifier = modifier)
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(customShapes.large)
            .background(blue_who.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Are you using Ice as Refrigerant?",style = customTypography.bodyMedium)
            OptionRadioButton(option = IceOption.Yes,
                selectedOption = selectedIceOption,
                onOptionSelected = { selectedIceOption = it })
            OptionRadioButton(option = IceOption.No,
                selectedOption = selectedIceOption,
                onOptionSelected = { selectedIceOption = it })

            if (selectedIceOption == IceOption.Yes) {

                // Display quantity  box when yes is selected
                OutlinedTextFieldComponent(
                    text = "Quantity in Kg",
                    value = iceQuantityText,
                    showError = iceQuantityError,
                    onValueChange = {
                        iceQuantityText = if (it.text.isDigitsOnly()) it else iceQuantityText
                        iceQuantityError = false // Hide the error message when the user starts typing
                    },
                    onDoneAction = {
                        keyboardController?.hide()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (iceQuantityError) {
                    ErrorMessage("Quantity is required!", modifier = Modifier)
                }
                OutlinedTextFieldComponent(
                    text = "Number of packages",
                    value = numPackages,
                    showError = numPackagesError,
                    onValueChange = {
                        numPackages = if (it.text.isDigitsOnly()) it else numPackages
                        numPackagesError =
                            false // Hide the error message when the user starts typing
                    },
                    onDoneAction = {
                        keyboardController?.hide()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (numPackagesError) {
                    ErrorMessage("Number of packages is required!", modifier = Modifier)
                }

            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        StartButton(onClick = {
            if (leaf.category == Category.A) {
                if (selectedSubstance.isNullOrEmpty()) {
                    substanceError = true
                } else {
                    assignedSubstance = selectedSubstance
                }
            }
            if (quantityText.text.isEmpty()) {
                quantityError = true
            } else {
                assignedQuantity = quantityText.text
            }
            if (assignedSubstance != null) {
                val specificSubstanceName = assignedSubstance
                val matchingElement = tableList.firstOrNull { it[0] == specificSubstanceName }
                if(leaf.unNumber == null){
                    leaf.unNumber = matchingElement?.get(1)?.takeLast(4)?.toInt()
                    if (leaf.unNumber == 2814) {
                        leaf.unSubstance = UnSubstance.ISHumans
                    } else if (leaf.unNumber == 2900) {

                        leaf.unSubstance = UnSubstance.ISAnimalsOnly
                    }
                }
                leaf.substanceName = assignedSubstance
            }

            if (assignedQuantity != null) {
                leaf.quantity = assignedQuantity?.toInt()
            }
            if (selectedIceOption == IceOption.No) {
                if(leaf.category == Category.A && (leaf.substanceName == null || assignedSubstance == null)) {
                    substanceError=true
                } else if ((leaf.category == Category.A || leaf.category == Category.B) && (leaf.quantity == null || assignedQuantity == null)) {
                    quantityError = true
                }
                else{
                    navController.navigate(
                        "${HomeNavigation.PackagingRoute}/" + "${leaf.category}/" + "${leaf.unNumber}/" + "${leaf.unSubstance}/" + "${leaf.quantity}/" + "${leaf.substanceName}/" + "${0}/" + "${0}"
                    ) {
                        launchSingleTop = true
                    }
                }
            } else {
                if (iceQuantityText.text.isEmpty()) {
                    iceQuantityError = true
                } else if (numPackages.text.isEmpty()) {
                    numPackagesError = true
                } else if ((leaf.category == Category.A || leaf.category == Category.B) && (leaf.quantity == null  || assignedQuantity == null)) {
                    quantityError = true
                } else if (leaf.category == Category.A && (leaf.substanceName == null || assignedSubstance == null)) {
                    substanceError = true
                } else {
                    assignedIceQuantity = iceQuantityText.text
                    assignedNumPackages = numPackages.text
                    navController.navigate(
                        "${HomeNavigation.PackagingRoute}/" + "${leaf.category}/" + "${leaf.unNumber}/" + "${leaf.unSubstance}/" + "${leaf.quantity}/" + "${leaf.substanceName}/" + "${assignedIceQuantity!!.toInt()}/" + "${assignedNumPackages}"

                    ) {
                        launchSingleTop = true
                    }
                }

            }
        }, modifier = Modifier)
    }
}

