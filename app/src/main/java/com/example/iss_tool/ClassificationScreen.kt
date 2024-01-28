package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.yellow_who

/**
 * argument unNumber skips the classification process by directly selecting a leaf
 * should only be provided when accessing screen from substance selection buttons in HomeScreen
 * **/
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClassificationScreen(navController: NavController, modifier: Modifier, unNumber: String? = null) {
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
            val keyboardController = LocalSoftwareKeyboardController.current
            val substanceList = listOf(
                "Bacillus anthracis (cultures only)",
                "Brucella abortus (cultures only)",
                "Brucella melitensis (cultures only)",
                "Brucella suis (cultures only)",
                "Burkholderia mallei – Pseudomonas mallei – Glanders (cultures only)",
                "Burkholderia pseudomallei – Pseudomonas pseudomallei (cultures only)",
                "Chlamydia psittaci – avian strains (cultures only)",
                "Clostridium botulinum (cultures only)",
                "Coccidioides immitis (cultures only)",
            )

            FormDisplay(leaf = leaf, substanceList = substanceList, Modifier, onDoneAction = {
                keyboardController?.hide()
            })
        }

    } else {
        throw Exception("currentNode must be of type ClassificationNode or ClassificationLeaf")
    }
}

