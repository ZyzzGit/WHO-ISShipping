package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.yellow_who

@Composable
fun ClassificationScreen(navController: NavController, paddingModifier: Modifier) {
    var currentNode by remember { mutableStateOf<Any?>(classificationDecisionTree) }

    if (currentNode is ClassificationNode) {
        Column(
            modifier = paddingModifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Show_logo(
                modifier = paddingModifier.align(Alignment.Start),
                id = R.drawable.who_logo,
                color = blue_who
            )
            Text(
                text = (currentNode as ClassificationNode).question,
                style = customTypography.bodyMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 22.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                BoxedFAB(
                    modifier = Modifier
                        .weight(1f)
                        .height(94.dp),
                    iconId = (currentNode as ClassificationNode).leftIconId!!,
                    iconLabel = (currentNode as ClassificationNode).leftIconLabel,
                    onClick = { currentNode = (currentNode as ClassificationNode).left },
                )
                BoxedFAB(
                    modifier = Modifier
                        .weight(1f)
                        .height(94.dp),
                    iconId = (currentNode as ClassificationNode).rightIconId!!,
                    iconLabel = (currentNode as ClassificationNode).rightIconLabel,
                    onClick = { currentNode = (currentNode as ClassificationNode).right },
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            if ((currentNode as ClassificationNode).additionalInfo != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.weight(0.056f),
                        painter = painterResource(id = R.drawable.info_icon),
                        contentDescription = "Info icon"
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        modifier = Modifier.weight(0.89f),
                        text = (currentNode as ClassificationNode).additionalInfo!!,
                        style = customTypography.bodyMedium.copy(fontSize = 10.sp),
                    )
                }
            }
        }
    } else if (currentNode is ClassificationLeaf) {
        var title: String = if ((currentNode as ClassificationLeaf).unSubstance != null) {
            (currentNode as ClassificationLeaf).unSubstance!!
        } else {
            (currentNode as ClassificationLeaf).category
        }

        Column(
            modifier = paddingModifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Show_logo(
                modifier = paddingModifier,
                id = R.drawable.who_logo,
                color = blue_who
            )
            Text(
                text = title,
                style = customTypography.bodyLarge,
                color = customColorScheme.primary
            )
            if ((currentNode as ClassificationLeaf).unNumber != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "UN ${(currentNode as ClassificationLeaf).unNumber.toString()}\n"
                            + (currentNode as ClassificationLeaf).category,
                    style = customTypography.bodyLarge,
                    color = yellow_who
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            if ((currentNode as ClassificationLeaf).additionalInfo != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.weight(0.056f),
                        painter = painterResource(id = R.drawable.info_icon),
                        contentDescription = "Info icon",
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        modifier = Modifier.weight(0.89f),
                        text = (currentNode as ClassificationLeaf).additionalInfo!!,
                        style = customTypography.bodyMedium.copy(fontSize = 10.sp),
                    )
                }
            }
        }
    } else {
        throw Exception("currentNode must be of type ClassificationNode or ClassificationLeaf")
    }
}