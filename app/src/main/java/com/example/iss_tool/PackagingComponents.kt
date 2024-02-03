package com.example.iss_tool

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iss_tool.theme.black
import com.example.iss_tool.theme.customShapes
import com.example.iss_tool.theme.customTypography
import com.example.iss_tool.theme.grey_who
import com.example.iss_tool.theme.primary_navy_blue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun packagingDisplay(modifier:Modifier,pageCount: Int,additional_text:String?=null){
    val pagerState = rememberPagerState(pageCount = {pageCount})
    HorizontalPager(state = pagerState) {
        if (pagerState.currentPage == 0) {
            Triplepackagingsystem(modifier,pagerState,pageCount)
        }
        else {
            additionalPackaging(modifier,pagerState,additional_text)
        }
    }
}

@Composable
fun layerDisplay(title:String,body:String,modifier: Modifier){
    Box(
        modifier = modifier
            .clip(customShapes.large)
            .requiredHeight(40.dp)
            .background(grey_who),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title",
            style = customTypography.bodyMedium,
            color = primary_navy_blue
        )

    }
    Text(
        text="$body",
        style = customTypography.bodySmall
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Triplepackagingsystem(modifier:Modifier,pagerState: PagerState,pageCount:Int){
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
            Text(
                text = (pagerState.currentPage+1).toString()+"/$pageCount",
                style= customTypography.bodySmall,
                color= black,
                modifier = modifier
                    .wrapContentWidth(Alignment.End)
            )

        }
        Image(
            painter = painterResource(R.drawable.packaging_image_a),
            contentDescription = "packaging",
            alignment = Alignment.Center
        )
        layerDisplay(title = "PRIMARY RECEPTABLE", body = "The primary receptacle, containing the infectious substance, must be watertight, and impermeable to the substance held within (i.e. leakproof – for liquid, or sift-proof – for solids). The primary receptacle should be appropriately labelled as to content. The primary receptacle must not become punctured, broken, weakened or affected by contact with the infectious substance. For example, the primary receptacle should not be corroded by preservation media used to hold a patient specimen. If the infectious substance contains a liquid, or semi-liquid substance, the primary receptacle must be wrapped in enough absorbent material to absorb all the fluid in the rare event of a breakage or leakage",
            modifier = modifier.align(Alignment.Start) )
        layerDisplay(title = "SECONDARY CONTAINER", body = "The second watertight, leakproof or sift\u0002proof container should then be used to enclose and protect the primary receptacle, and its absorbent material. Several primary receptacles may be placed in a single secondary container, provided they are all infectious substances of the same class. If the primary receptacle is fragile, each must be wrapped and placed in the secondary container individually, or in a way that prevents contact between them. Cushioning material may be required to secure the primary receptacles within the secondary container",
            modifier = modifier.align(Alignment.Start) )
        layerDisplay(title = "OUTER PACKAGING", body = "A third, outer layer of packaging is used to protect the secondary container from physical damage while in transit. It must therefore be of an appropriate strength for the weight, size and composition of the inner packages to be protected. At least one surface of the outer packaging must have a minimum dimension of 100 mm × 100 mm.",
            modifier = modifier.align(Alignment.Start) )

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun additionalPackaging(modifier:Modifier,pagerState: PagerState,text1_2: String?){
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
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = (pagerState.currentPage+1).toString()+"/2",
                style= customTypography.bodySmall,
                color= black,
                modifier = modifier
                    .weight(.1f)
                    .wrapContentWidth(Alignment.End)
            )

        }
        layerDisplay(title = "ADDITIONAL REQUIREMENTS", body = "$text1_2", modifier = modifier.align(Alignment.Start) )
    }}
