package com.example.iss_tool

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.blue_who

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
        Text(text = "Classification!")
    }
}