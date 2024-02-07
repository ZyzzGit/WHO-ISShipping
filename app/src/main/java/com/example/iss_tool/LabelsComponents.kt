package com.example.iss_tool

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customTypography

@Composable
fun InfoBox(modifier: Modifier, title: String, name: String, address: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, black, shape = RectangleShape)
    ) {
        Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = modifier,
                text = title,
                style = customTypography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier,
                text = name,
                style = customTypography.bodyMedium,
                textAlign = TextAlign.Center

            )
            Text(
                modifier = modifier,
                text = address,
                style = customTypography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}


