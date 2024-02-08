package com.example.iss_tool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography

@Composable
fun InfoScreen(modifier: Modifier) {
    Column (
        modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(space = 11.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(R.drawable.info_icon), contentDescription = "info_logo")
            Text(
                text="Information",
                style = customTypography.bodyLarge,
                color = customColorScheme.primary
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "The importance and necessity of shipping infectious substances safely is evident due to errors, " +
                "confusion, and potential risks caused by rather complex process rules.\n\n" +
                "The ISS Tool aims to guide and simplify the process of classifying and safely shipping infectious substances.",
            style = customTypography.bodySmall
        )

        Spacer(modifier = Modifier.height(20.dp))
        IconBody(iconId = R.drawable.warning_icon, text = "Be aware that this tool is a work in progress, and should be used with great caution.")

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Developed by\nNicolas Johansson\nZeina Hassoun",
            style = customTypography.bodySmall,
            color = customColorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))
        IconBody(iconId = R.drawable.copyright_icon, text = "2024\nAll rights reserved", color = Color.Gray)
    }
}