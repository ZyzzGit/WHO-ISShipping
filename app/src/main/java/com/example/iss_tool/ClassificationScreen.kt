package com.example.iss_tool

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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
import com.example.iss_tool.database.Substance
import com.example.iss_tool.database.SubstanceViewModel
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.yellow_who


/**
 * argument unNumber skips the classification process by directly selecting a leaf
 * should only be provided when accessing screen from substance selection buttons in HomeScreen
 * **/
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClassificationScreen(
    navController: NavController, modifier: Modifier, unNumber: String? = null
) {
    var currentNode by remember { mutableStateOf<Any?>(classificationDecisionTree) }

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
                text = node.question,
                style = customTypography.bodyMedium,
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

        val title: String = leaf.unSubstance?.toString() ?: leaf.category.toString()

        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title, style = customTypography.bodyLarge, color = customColorScheme.primary
            )
            if (leaf.unNumber != null && leaf.category != Category.Exempt) {
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



            //Retrieve substance from Room Database
//            var substanceList = listOf("")
//            val substanceViewModel: SubstanceViewModel = viewModel()
//
//            val allData by substanceViewModel._readAllData.observeAsState(emptyList())
//            var tableList = listOf<List<String>>()
            var substanceList = listOf("")
            val substanceViewModel: SubstanceViewModel = viewModel()

            // Define allData as a State object with an initial value of emptyList()
            var allData by mutableStateOf(emptyList<Substance>())

            // Conditionally assign the value of allData based on a condition
            if (leaf.unNumber == null) {
                allData = substanceViewModel._readAllData.observeAsState(emptyList()).value
            } else if(leaf.unNumber == 2900){
                allData = substanceViewModel._readAnimalSub.observeAsState(emptyList()).value
            }
            else if(leaf.unNumber == 2814){
                allData = substanceViewModel._readHumanSub.observeAsState(emptyList()).value
            }

            // Define tableList
            var tableList = listOf<List<String>>()
            allData.forEach {
                substanceList += it.substanceName ?: ""
            }
            tableList = allData.map { substance ->
                listOf(substance.substanceName ?: "", substance.code ?: "")
            }
            if(leaf.category != Category.Exception){
            FormDisplay(
                navController = navController,
                leaf = leaf,
                substanceList = substanceList,
                tableList = tableList,

                Modifier
            ) {
                keyboardController?.hide()
            }
            }
        }

    } else {
        throw Exception("currentNode must be of type ClassificationNode or ClassificationLeaf")
    }
}


