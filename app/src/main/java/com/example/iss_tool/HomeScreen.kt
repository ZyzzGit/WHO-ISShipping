package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.white

@Composable
fun HomeScreen(paddingModifier: Modifier) {
    Column (
        modifier = paddingModifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Show_logo(modifier = paddingModifier, id = R.drawable.who_logo, color = blue_who)
        Text(
            text = "Let's Get Started!",
            style = customTypography.bodyLarge,
            color = customColorScheme.primary)
        Text(text = "Choose your substance to be shipped",
            style = customTypography.bodyMedium,
            color = black
        )
        Row (
            modifier = paddingModifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SubstanceFrame(
                text = "Infectious Substance\n Affecting Humans",
                unNumber = 2814
            )
            SubstanceFrame(
                text = "Infectious Substance\n Affecting Animals",
                unNumber = 2900
            )
        }
    }
}



