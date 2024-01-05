package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customTypography

var testQuestion = "Could the Specimen or Substance be Infectious?"

@Composable
fun ClassificationScreen(navController: NavController, paddingModifier: Modifier) {
    Column (
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
        Text(text = testQuestion, style = customTypography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Row (
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
                iconId = R.drawable.check,
                onClick = { /*TODO*/ },
            )
            BoxedFAB(
                modifier = Modifier
                    .weight(1f)
                    .height(94.dp),
                iconId = R.drawable.close,
                text = "maybe some label",
                onClick = { /*TODO*/ },
            )
        }
    }
}