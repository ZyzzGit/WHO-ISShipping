package com.example.iss_tool

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.orange_who


enum class IceOption(val text: String) {
    Yes("YES"),
    No("NO")
}

@Composable
fun OptionRadioButton(
    option: IceOption,
    selectedOption: IceOption?,
    onOptionSelected: (IceOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) }
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) },
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(option.text, style = customTypography.bodyMedium)
    }
}

@Composable
fun Shipment(modifier: Modifier, shipmentMethod: String, title: String, infoBody: String) {
    Text(
        text = title,
        style = customTypography.bodyLarge,
        color = orange_who,
    )
    NoteText(
        modifier = modifier,
        id = R.drawable.info_icon,
        iconInfo = "info",
        textInfo = infoBody
    )
}

@Composable
fun NoteText(modifier: Modifier, id: Int, iconInfo: String, textInfo: String) {
    Row(modifier = modifier.fillMaxWidth()) {
        Icon(painter = painterResource(id = id), contentDescription = iconInfo)
        Text(
            text = textInfo,
            style = customTypography.bodySmall,
            color = black
        )
    }
}