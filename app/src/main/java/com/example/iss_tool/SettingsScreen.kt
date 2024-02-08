package com.example.iss_tool

import android.widget.ToggleButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.grey_who
import com.example.iss_tool.theme.primary_navy_blue

@Composable
fun SettingsScreen(modifier: Modifier) {
    var notificationisChecked by remember { mutableStateOf(false) }
    var updatesisChecked by remember { mutableStateOf(false) }
    var darkisChecked by remember { mutableStateOf(false) }
    Column (
        modifier
            .padding(24.dp)
            .fillMaxWidth()
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(painter = painterResource(R.drawable.settings_icon),
                contentDescription = "description"
            )
            Text(text="Settings", style= customTypography.bodyLarge,color = primary_navy_blue)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.weight(1f),text="Notifications", style= customTypography.bodyMedium,color = black)
            Switch(
                checked = notificationisChecked,
                onCheckedChange = { notificationisChecked = it },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.weight(1f),text="Updates", style= customTypography.bodyMedium,color = black)
            Switch(
                checked = updatesisChecked,
                onCheckedChange = { updatesisChecked = it },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.weight(1f),text="Dark Mode", style= customTypography.bodyMedium,color = black)
            Switch(
                checked = darkisChecked,
                onCheckedChange = { darkisChecked = it },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.weight(1f),text="Language", style= customTypography.bodyMedium,color = black)
            Image(painter = painterResource(R.drawable.english_flag),
                contentDescription = "English",
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .wrapContentWidth(Alignment.End)
                    .size(20.dp)

            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(painter = painterResource(R.drawable.italian_flag),
                contentDescription = "Italiano",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentWidth(Alignment.End)
                    .size(20.dp)

            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(modifier = Modifier,text="More settings", style= customTypography.bodyMedium,color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))
        Text(modifier = Modifier,text="Help", style= customTypography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Text(modifier = Modifier,text="Feedback", style= customTypography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Text(modifier = Modifier,text="About us", style= customTypography.bodyMedium)


    }
}
