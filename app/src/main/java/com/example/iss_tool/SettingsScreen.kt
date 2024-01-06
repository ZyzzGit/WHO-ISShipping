package com.example.iss_tool

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iss_tool.theme.blue_who

@Composable
fun SettingsScreen(paddingModifier: Modifier) {
    Column (
        paddingModifier
            .padding(24.dp)
            .fillMaxWidth()
    ){
        Text(text="Settings", fontSize = 50.sp)
    }
}