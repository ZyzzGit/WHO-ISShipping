package com.example.iss_tool.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun MyCustomTheme(content: @Composable () -> Unit) {
    // Apply the custom color scheme to the MaterialTheme
    MaterialTheme(
        colorScheme = customColorScheme,
        typography = customTypography,
        shapes = customShapes,
        content = content
    )
}
