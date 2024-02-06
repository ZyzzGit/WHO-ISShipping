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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Triplepackagingsystem(modifier:Modifier,category:Category,ice:String){
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

        primaryReceptable(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice =ice)

        Spacer(Modifier.height(8.dp))

        primaryReceptableEssentials(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)

        Spacer(Modifier.height(8.dp))

        secondaryPackaging(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)

        if(category == Category.B && ice == "NO"){
           categoryBNoice(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)
        }
        Spacer(Modifier.height(8.dp))

        outerPackaging(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)

        if(category == Category.A){
        categoryAapproval(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)
        }
        Spacer(Modifier.height(8.dp))
        if(category == Category.Exempt && ice == "YES"){

        }
        else{
            packaging2To3(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)}
        if(ice =="YES"){
            icePackaging(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)

        }
        if(category == Category.B){
            additionalBpackaging(modifier = modifier.align(Alignment.CenterHorizontally), category = category, ice = ice)
        }


    }
}


@Composable
fun primaryReceptable(modifier: Modifier,category:Category,ice:String){
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("1. PRIMARY RECEPTABLE",style= customTypography.bodyMedium)
            Text("- It shall be leakproof;" ,
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            if(ice == "YES" && category != Category.Exempt){
                Text("- It shall maintain its integrity at the temperature of dry ice;" ,
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            if(category == Category.A){
                Text("- It shall be of glass,metal or plastics;\n" +
                        "- Positive means of ensuring a leakproof seal shall be provided, i.e. a heat seal. a skirted stopper or a metal crimp steal. If screw caps are used, they should be secured by positive means, i.e. tape,paraffin sealing tape or manufactured locking closure.\n" +
                        "Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), as well astemperatures in the range of -40°C to +55°C." ,
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            if(category == Category.B){
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
}


@Composable
fun primaryReceptableEssentials(modifier: Modifier,category:Category,ice:String){
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("If multiple primary receptables are placed in a single secondary packages, they shall be either individually wrapped or separated to prevent contact between them.\n" +
                    "For liquids, an absorbant material in sufficient quantity should be placed to absorb the entire contents placed between the primary receptables(s) and the secondary packaging.",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            Image(
                painter = painterResource(R.drawable.packaging_absorbent_materials),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
    }
}

@Composable
fun secondaryPackaging(modifier: Modifier,category:Category,ice:String){
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("2. SECONDARY PACKAGING",style= customTypography.bodyMedium)
            Text("- It shall be leakproof;",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            if(ice == "YES" && category != Category.Exempt){
                Text("- It shall maintain its integrity at the temperature of dry ice;" ,
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            if(category == Category.A){
                Text("Whatever the intended temperature of the consignment, the primary receptacle OR the secondary packaging must be capable of withstanding a pressure differential of not less than 95kPA (0.95 bar), as well astemperatures in the range of -40°C to +55°C." ,
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            if(category == Category.B){
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
}

@Composable
fun categoryBNoice(modifier: Modifier,category:Category,ice:String){
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Secondary packaging must be secured in outer packaging with suitable cushioning material",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            Image(
                painter = painterResource(R.drawable.packaging_categoryb_cushioning),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
    }
}

@Composable
fun outerPackaging(modifier: Modifier,category:Category,ice:String){
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("3. OUTER PACKAGING",style= customTypography.bodyMedium)
            Text("- It must be strong;\n" +
                    "- At least one surface of the outer packaging shall have a minimum dimension of 100 x 100 mm",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            if(category == Category.A || category == Category.B){
                Text("- It must be UN approved Class 6.2",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            Image(
                painter = painterResource(R.drawable.triple_packaging_3),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
    }
}

@Composable
fun categoryAapproval(modifier: Modifier,category:Category,ice:String){
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("In the certificate of approval shall be expressly indicated the type of packaging in which it was carried out the aaproval." +
                    "The UN certified fibreboard 4G box (4G/CLASS6.2...) is suitable only combining the specific inner packagings with which it has undergoing testing \n" +
                    "If the inner packaging that you want to use is not between inner packagings listed ont the certificate, you must use an UN approved packaging with the letter U, (4GU/CLASS 6.2...) which is suitable for any type of vials.",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
        }
    }
}

@Composable
fun packaging2To3(modifier: Modifier,category:Category,ice:String){
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            if(category == Category.A){
                Text("Put the secondary packaging into the outer UN approved Class 6.2(4GU).\n" +
                        "An itemized list of contents shall be enclosed between the secondary container and outer packaging, including the proper shipping name and technical name in parentheses of the biological agent present in the infectious substance. When the infectious substance to be transported are unknown, but suspected of meeting the criteria for inclusion in Category A, the words “suspected Category A infectious substance” must be shown in parentheses following the proper shipping name.\n" +
                        "If the packaging is not approved 4GU/CLASS 6.2..., you must verify that your packaging is the same as the prototype tested.",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }
            else if(category == Category.B && ice == "NO"){
                Text("Put the suitable cushioning material and the secondary packaging into the outer packaging\n" +
                        "An itemized list of contents shall be enclosed between the secondary container and outer packaging.\n" +
                        "Close the Outer packaging",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            }

            Text("Put the secondary packaging into the outer packaging",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)

            Image(
                painter = painterResource(R.drawable.triple_packaging_23),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}

    }
}


@Composable
fun icePackaging(modifier: Modifier,category:Category,ice:String){
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("POLYSTRENE BOX",style= customTypography.bodyMedium)
            Text("The polystrene permit the release of carbon dioxide gas to prevent to build-up pressure that could rupture the packagings.",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)
            Image(
                painter = painterResource(R.drawable.polystrene_box),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}
    }
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
            if(category == Category.A){
                Text("Put the marked and labelled package in the polystrene box. ",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)}
            Text("Interior supports shall be provided to secure the package in the original position after dry ice, inserted into free spaces, has dissipated.",
                Modifier.align(Alignment.Start),style= customTypography.bodySmall)

            Image(
                painter = painterResource(R.drawable.ice_box),
                contentDescription = "packaging",
                alignment = Alignment.Center
            )}


    }
    if(category == Category.A){
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .background(white)){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("Put the Polystrene box containing the fibreboard box UN approved class 6.2(4GU) properly fixed in an overpack.\n" +
                        "Close the overpack (outer fibreboard box) that shall be labelled and marked as an overpack.",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                Image(
                    painter = painterResource(R.drawable.ice_box_a),
                    contentDescription = "packaging",
                    alignment = Alignment.Center
                )}
        }
    }
    if(category == Category.B || category == Category.Exempt){
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .background(white)){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Text("Put the polysterene box and the secondary packaging properly fixed in the outer packaging."+
                        "An itemized list of contents shall be enclosed between the secondary container and outer packaging.\n" +
                        "Close the outer packaging",
                    Modifier.align(Alignment.Start),style= customTypography.bodySmall)
                Image(
                    painter = painterResource(R.drawable.ice_box_a),
                    contentDescription = "packaging",
                    alignment = Alignment.Center
                )}
        }
    }
}

@Composable
fun additionalBpackaging(modifier: Modifier,category:Category,ice:String){
    Spacer(Modifier.height(8.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .background(white)){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
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