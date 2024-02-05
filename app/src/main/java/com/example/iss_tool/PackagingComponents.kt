package com.example.iss_tool

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.primary_navy_blue
import com.example.iss_tool.theme.white

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun packagingDisplay(modifier:Modifier,pageCount: Int,additional_text:String?=null){
//    val pagerState = rememberPagerState(pageCount = {pageCount})
//    HorizontalPager(state = pagerState) {
//        if (pagerState.currentPage == 0) {
//            Triplepackagingsystem(modifier,pagerState,pageCount)
//        }
//        else {
//            additionalPackaging(modifier,pagerState,additional_text)
//        }
//    }
//}

//@Composable
//fun layerDisplay(title:String,body:String,modifier: Modifier){
//    Box(
//        modifier = modifier
//            .clip(customShapes.large)
//            .requiredHeight(40.dp)
//            .background(grey_who),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "$title",
//            style = customTypography.bodyMedium,
//            color = primary_navy_blue
//        )
//
//    }
//    Text(
//        text="$body",
//        style = customTypography.bodySmall
//    )
//}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun Triplepackagingsystem(modifier:Modifier,pagerState: PagerState,pageCount:Int){
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(bottom = 5.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = "TRIPLE PACKAGING SYSTEM",
//                style = customTypography.bodyMedium,
//                color = primary_navy_blue,
//                modifier = modifier
//                    .weight(1f)
//                    .wrapContentWidth(Alignment.CenterHorizontally)
//            )
//            Text(
//                text = (pagerState.currentPage+1).toString()+"/$pageCount",
//                style= customTypography.bodySmall,
//                color= black,
//                modifier = modifier
//                    .wrapContentWidth(Alignment.End)
//            )
//
//        }
//        Image(
//            painter = painterResource(R.drawable.packaging_image_a),
//            contentDescription = "packaging",
//            alignment = Alignment.Center
//        )
//        layerDisplay(title = "PRIMARY RECEPTABLE", body = "The primary receptacle, containing the infectious substance, must be watertight, and impermeable to the substance held within (i.e. leakproof – for liquid, or sift-proof – for solids). The primary receptacle should be appropriately labelled as to content. The primary receptacle must not become punctured, broken, weakened or affected by contact with the infectious substance. For example, the primary receptacle should not be corroded by preservation media used to hold a patient specimen. If the infectious substance contains a liquid, or semi-liquid substance, the primary receptacle must be wrapped in enough absorbent material to absorb all the fluid in the rare event of a breakage or leakage",
//            modifier = modifier.align(Alignment.Start) )
//        layerDisplay(title = "SECONDARY CONTAINER", body = "The second watertight, leakproof or sift\u0002proof container should then be used to enclose and protect the primary receptacle, and its absorbent material. Several primary receptacles may be placed in a single secondary container, provided they are all infectious substances of the same class. If the primary receptacle is fragile, each must be wrapped and placed in the secondary container individually, or in a way that prevents contact between them. Cushioning material may be required to secure the primary receptacles within the secondary container",
//            modifier = modifier.align(Alignment.Start) )
//        layerDisplay(title = "OUTER PACKAGING", body = "A third, outer layer of packaging is used to protect the secondary container from physical damage while in transit. It must therefore be of an appropriate strength for the weight, size and composition of the inner packages to be protected. At least one surface of the outer packaging must have a minimum dimension of 100 mm × 100 mm.",
//            modifier = modifier.align(Alignment.Start) )
//
//    }
//}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun additionalPackaging(modifier:Modifier,pagerState: PagerState,text1_2: String?){
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(bottom = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = (pagerState.currentPage+1).toString()+"/2",
//                style= customTypography.bodySmall,
//                color= black,
//                modifier = modifier
//                    .weight(.1f)
//                    .wrapContentWidth(Alignment.End)
//            )
//
//        }
//        layerDisplay(title = "ADDITIONAL REQUIREMENTS", body = "$text1_2", modifier = modifier.align(Alignment.Start) )
//    }}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Triplepackagingsystem(modifier:Modifier,category:String,ice:Boolean){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "TRIPLE PACKAGING SYSTEM",
                style = customTypography.bodyMedium,
                color = primary_navy_blue,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("1. PRIMARY RECEPTABLE",style= customTypography.bodyMedium)
                Text("- It shall be leakproof;" ,
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                if(category == "Category A"){
                    Text("- It shall be of glass,metal or plastics;\n" +
                            "- Positive means of ensuring a leakproof seal shall be provided, i.e. a heat seal. a skirted stopper or a metal crimp steal. If screw caps are used, they should be secured by positive means, i.e. tape,paraffin sealing tape or manufactured locking closure.\n" +
                            "Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), as well astemperatures in the range of -40°C to +55°C." ,
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                if(category == "Category B"){
                    Text("- It must be siftproof\n" +
                            "Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar)." ,
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                Image(
                painter = painterResource(R.drawable.triple_packaging_1),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("If multiple primary receptables are placed in a single secondary packages, they shall be either individually wrapped or separated to prevent contact between them.\n" +
                        "For liquids, an absorbant material in sufficient quantity should be placed to absorb the entire contents placed between the primary receptables(s) and the secondary packaging.",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                Image(
                painter = painterResource(R.drawable.packaging_absorbent_materials),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("2. SECONDARY PACKAGING",style= customTypography.bodyMedium)
                Text("- It shall be leakproof;",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                if(category == "Category A"){
                    Text("Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), as well astemperatures in the range of -40°C to +55°C." ,
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                if(category == "Category B"){
                    Text("- It must be siftproof\n" +
                            "Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar)." ,
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                Image(
                painter = painterResource(R.drawable.triple_packaging_2),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        if(category == "Category B"){
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
                Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Secondary packaging must be secured in outer packaging with suitable cushioning material",
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                    Image(
                        painter = painterResource(R.drawable.packaging_categoryb_cushioning),
                        contentDescription = "packaging",
                        alignment = Alignment.Center
                    )}
            }
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("3. OUTER PACKAGING",style= customTypography.bodyMedium)
                Text("- It must be strong;\n" +
                        "- At least one surface of the outer packaging shall have a minimum dimension of 100 x 100 mm",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                if(category == "Category A" || category == "Category B"){
                    Text("- It must be UN approved Class 6.2",
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                Image(
                painter = painterResource(R.drawable.triple_packaging_3),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        if(category == "Category A"){
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("In the certificate of approval shall be expressly indicated the type of packaging in which it was carried out the aaproval." +
                        "The UN certified fibreboard 4G box (4G/CLASS6.2...) is suitable only combining the specific inner packagings with which it has undergoing testing \n" +
                        "If the inner packaging that you want to use is not between inner packagings listed ont the certificate, you must use an UN approved packaging with the letter U, (4GU/CLASS 6.2...) which is suitable for any type of vials.",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
        }}
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                if(category == "Category A"){
                    Text("Put the secondary packaging into the outer UN approved Class 6.2(4GU).\n" +
                            "An itemized list of contents shall be enclosed between the secondary container and outer packaging, including the proper shipping name and technical name in parentheses of the biological agent present in the infectious substance. When the infectious substance to be transported are unknown, but suspected of meeting the criteria for inclusion in Category A, the words “suspected Category A infectious substance” must be shown in parentheses following the proper shipping name.\n" +
                            "If the packaging is not approved 4GU/CLASS 6.2..., you must verify that your packaging is the same as the prototype tested.",
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                else if(category == "Category B"){
                    Text("Put the suitable cushioning material and the secondary packaging into the outer packaging\n" +
                            "An itemized list of contents shall be enclosed between the secondary container and outer packaging.\n" +
                            "Close the Outer packaging",
                        Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                }
                else {
                Text("Put the secondary packaging into the outer packaging",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)}
                Image(
                painter = painterResource(R.drawable.triple_packaging_23),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        if(category == "Category B"){
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(white)){
            Column(Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("PARTICULAR TESTS",style= customTypography.bodyMedium)
                Text("The complete triple package must be capable of passing a 1.2m ‘drop test’, to prove that is of an appropriate strength and quality.",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                Image(
                painter = painterResource(R.drawable.drop_test),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
        }
        }


    }
}
