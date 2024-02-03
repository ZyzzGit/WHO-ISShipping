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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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

//        Box(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .clip(customShapes.large)
//                    .background(blue_who.copy(alpha = 0.2f))
//                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
//            ) {
//                Column(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .verticalScroll(rememberScrollState()),
////                horizontalAlignment = Alignment.CenterHorizontally
//                ){ layerDisplay(title = "Packing in Overpacks", body = "\"Overpack\" is an enclosure used by a single shipper to contain one or more packages and to form one handling unit for convenience of handling and stowage.\n" +
//                        "If dry ice is being used to protect contents, the overpacks may be comprised of insulated vessels or flasks to allow dissipation of carbon dioxide gas.\n" +
//                        " Whenever an overpack is used, the required marks and labels shown on the packages of infectious substance inside must be repeated on the outermost layer of the overpack (unless already clearly visible, for example through a clear plastic wrapping).\n" +
//                        " Overpacks must be marked with the word “OVERPACK” in lettering at least 12mm high.\n",
//                    modifier = modifier.align(Alignment.Start))
//                }
//            }



}

@Composable
fun noteText(modifier:Modifier,id:Int,icon_info:String,textInfo:String){
    Row(modifier = modifier.fillMaxWidth()){
        Icon(painter = painterResource(id = id), contentDescription ="$icon_info")
        Text(text="$textInfo",
            style = customTypography.bodySmall,
            color = black)}
}