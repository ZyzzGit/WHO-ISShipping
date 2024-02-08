package com.example.iss_tool

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
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


@Composable
fun MarksDisplay(modifier: Modifier,imageId:Int,description:String){
    Image(
        painter = painterResource(imageId),
        contentDescription = description,
        modifier = modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(shape = MaterialTheme.shapes.large)
    )
}