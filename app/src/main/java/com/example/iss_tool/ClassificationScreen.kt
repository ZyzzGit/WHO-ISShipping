package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.yellow_who


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClassificationScreen(navController: NavController, modifier: Modifier) {
    var currentNode by remember { mutableStateOf<Any?>(classificationDecisionTree) }

    if (currentNode is ClassificationNode) {
        val node = (currentNode as ClassificationNode)

        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = node.question, style = customTypography.bodyMedium
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
            if (leaf.unNumber != null) {
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
            // Reference to the software keyboard controller
            val keyboardController = LocalSoftwareKeyboardController.current

            if (leaf.category == "Category A") {
                //choose substance from the list
            }
            if (leaf.category == "Category A" || leaf.category == "Category B") {
                Spacer(modifier = Modifier.height(24.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    QuantityDisplay(leaf, Modifier, onDoneAction = {
                        keyboardController?.hide()
                    })

                }


            }
        }

    } else {
        throw Exception("currentNode must be of type ClassificationNode or ClassificationLeaf")
    }
}




