package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.iss_tool.database.SubstanceViewModel
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.yellow_who
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState


/**
 * argument unNumber skips the classification process by directly selecting a leaf
 * should only be provided when accessing screen from substance selection buttons in HomeScreen
 * **/
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClassificationScreen(navController: NavController, modifier: Modifier, unNumber: String? = null) {
    var currentNode by remember { mutableStateOf<Any?>(classificationDecisionTree) }
//    /**
//    Read substances from database
//     * **/
//    val substanceViewModel: SubstanceViewModel = viewModel()
//    val substances = substanceViewModel._readAllData
//    var list by remember { mutableStateOf<List<String>>(listOf()) }
//    substances?.forEach {
//        list += it.substanceName.toString()
//    }

    if (unNumber != null) {
        currentNode = getLeaf(unNumber)
    }

    if (currentNode is ClassificationNode) {
        val node = (currentNode as ClassificationNode)

        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = node.question, style = customTypography.bodyMedium,
                modifier = Modifier.height(170.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
                    space = 22.dp, alignment = Alignment.CenterHorizontally
                )
            ) {
                BoxedFAB(
                    modifier = Modifier
                        .weight(1f)
                        .height(94.dp),
                    iconId = node.leftIconId!!,
                    iconLabel = node.leftIconLabel,
                    onClick = { currentNode = node.left },
                )
                BoxedFAB(
                    modifier = Modifier
                        .weight(1f)
                        .height(94.dp),
                    iconId = node.rightIconId!!,
                    iconLabel = node.rightIconLabel,
                    onClick = { currentNode = node.right },
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (node.additionalInfo != null) {
                InfoBody(infoText = node.additionalInfo)
            }
        }
    } else if (currentNode is ClassificationLeaf) {
        val leaf = (currentNode as ClassificationLeaf)

        val title: String = leaf.unSubstance ?: leaf.category

        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title, style = customTypography.bodyLarge, color = customColorScheme.primary
            )
            if (leaf.unNumber != null && leaf.category != "Exempt Human or Animal Specimen") {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "UN ${leaf.unNumber}\n" + leaf.category,
                    style = customTypography.bodyLarge,
                    color = yellow_who
                )
            }
            if (leaf.additionalInfo != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = leaf.additionalInfo, style = customTypography.bodySmall
                )
            }

            val keyboardController = LocalSoftwareKeyboardController.current
            var substanceList = listOf("")
            val substanceViewModel: SubstanceViewModel = viewModel()
            val allData by substanceViewModel._readAllData.observeAsState(emptyList())
            var tableList = listOf<List<String>>()
            allData.forEach {
                substanceList += it.substanceName?:""
            }
            tableList = allData.map { substance ->
                listOf(substance.substanceName ?: "", substance.code ?: "")
            }

            if (leaf.category == "Category A" || leaf.category == "Category B") {
            FormDisplay(navController=navController,leaf = leaf, substanceList = substanceList, tableList=tableList,Modifier) {
                keyboardController?.hide()
                }


            }
            else if(leaf.category == "Exempt Human or Animal Specimen"){
                leaf.unNumber = 0
                leaf.quantity = 0
                leaf.unSubstance = "Exempt Human or Animal Specimen"
                leaf.substanceName=" "
                ExemptDisplay(navController = navController, leaf = leaf, modifier = Modifier)
            }
        }

    } else {
        throw Exception("currentNode must be of type ClassificationNode or ClassificationLeaf")
    }
}


