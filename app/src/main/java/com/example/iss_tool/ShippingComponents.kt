package com.example.iss_tool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.blue_who
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.grey_who
import com.example.iss_tool.theme.orange_who
import com.example.iss_tool.theme.white





enum class IceOption(val text: String) {
    Option1("YES"),
    Option2("NO")
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
//            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) },
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(option.text)
    }
}

@Composable
fun shipment(modifier: Modifier,shipmentMethod:String,title:String,info_body:String){
        Text(
                text = "$title",
                style = customTypography.bodyLarge,
                color = orange_who,
        )
        noteText(modifier = modifier,
        id=R.drawable.info_icon,
        icon_info = "info",
        textInfo ="$info_body")
        Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun noteText(modifier:Modifier,id:Int,icon_info:String,textInfo:String){
    Row(modifier = modifier.fillMaxWidth()){
        Icon(painter = painterResource(id = id), contentDescription ="$icon_info")
        Text(text="$textInfo",
            style = customTypography.bodySmall,
            color = black)}
}