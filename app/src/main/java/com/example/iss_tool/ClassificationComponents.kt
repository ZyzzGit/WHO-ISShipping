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
    var showErrorsubstance by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    // State for quantity input
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var assignedValue by remember { mutableStateOf<String?>(null) }
    var showError by remember { mutableStateOf(false) }

    //Assigned values for substance and quantity
    var assignedValue2 by remember { mutableStateOf<String?>(null) }
    var selectedSubstance by remember { mutableStateOf(substanceList[0]) }

    // State for ice quantity input
    var text2 by remember { mutableStateOf(TextFieldValue("")) }
    var showError2 by remember { mutableStateOf(false) }

    //State for number of packages input
    var numberofpackages by remember { mutableStateOf(TextFieldValue("")) }
    var numberofpackagesError by remember { mutableStateOf(false) }
    var assignedNbofpackages by remember { mutableStateOf<String?>(null) }


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
                                showErrorsubstance = false

                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }

            }
            if (showErrorsubstance) {
                ErrorMessage(message = "Substance is required")
            }
        }

    }
    if (leaf.category != Category.Exempt) {
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextFieldComponent(
            text = "Quantity in mL or g",
            value = text,
            showError = showError,
            onValueChange = {
                text = if (it.text.isDigitsOnly()) it else text
                showError = false // Hide the error message when the user starts typing
            },
            onDoneAction = {
                onDoneAction
            },
            modifier = modifier.fillMaxWidth()
        )

        if (showError) {
            ErrorMessage("Quantity is required!", modifier = modifier)
        }
    }

    var selectedOption by remember { mutableStateOf<IceOption?>(IceOption.No) }
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
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it })
            OptionRadioButton(option = IceOption.No,
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it })

            if (selectedOption == IceOption.Yes) {

                // Display quantity  box when yes is selected
                OutlinedTextFieldComponent(
                    text = "Quantity in Kg",
                    value = text2,
                    showError = showError2,
                    onValueChange = {
                        text2 = if (it.text.isDigitsOnly()) it else text2
                        showError2 = false // Hide the error message when the user starts typing
                    },
                    onDoneAction = {
                        keyboardController?.hide()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (showError2) {
                    ErrorMessage("Quantity is required!", modifier = Modifier)
                }
                OutlinedTextFieldComponent(
                    text = "Number of packages",
                    value = numberofpackages,
                    showError = numberofpackagesError,
                    onValueChange = {
                        numberofpackages = if (it.text.isDigitsOnly()) it else numberofpackages
                        numberofpackagesError =
                            false // Hide the error message when the user starts typing
                    },
                    onDoneAction = {
                        keyboardController?.hide()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (numberofpackagesError) {
                    ErrorMessage("Number of packages is required!", modifier = Modifier)
                }

            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        StartButton(onClick = {
            if (leaf.category == Category.A) {
                if (selectedSubstance.isNullOrEmpty()) {
                    showErrorsubstance = true
                } else {
                    assignedSubstance = selectedSubstance
                }
            }
            if (text.text.isEmpty()) {
                showError = true
            } else {
                assignedValue = text.text
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

            if (assignedValue != null) {
                leaf.quantity = assignedValue?.toInt()
            }
            if (selectedOption == IceOption.No) {
                if((leaf.category == Category.A || leaf.category == Category.B) && assignedSubstance ==null){
                    showErrorsubstance=true
                }
                else{
                    navController.navigate(
                        "${HomeNavigation.PackagingRoute}/" + "${leaf.category}/" + "${leaf.unNumber}/" + "${leaf.unSubstance}/" + "${leaf.quantity}/" + "${leaf.substanceName}/" + "${0}/" + "${0}"
                    ) {
                        launchSingleTop = true
                    }
                }
            } else {
                if (text2.text.isEmpty()) {
                    showError2 = true
                } else if (numberofpackages.text.isEmpty()) {
                    numberofpackagesError = true
                } else {
                    assignedValue2 = text2.text
                    assignedNbofpackages = numberofpackages.text
                    navController.navigate(
                        "${HomeNavigation.PackagingRoute}/" + "${leaf.category}/" + "${leaf.unNumber}/" + "${leaf.unSubstance}/" + "${leaf.quantity}/" + "${leaf.substanceName}/" + "${assignedValue2!!.toInt()}/" + "${assignedNbofpackages}"

                    ) {
                        launchSingleTop = true
                    }
                }

            }
        }, modifier = Modifier)
    }
}

