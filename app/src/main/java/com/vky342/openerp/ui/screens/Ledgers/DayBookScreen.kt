package com.vky342.openerp.ui.screens.Ledgers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.theme.Greye

@Preview
@Composable
fun DayBookScreen(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1


    Column(modifier = Modifier
        .padding(top = topPadding.dp, bottom = topPadding.dp)
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black,
                    Greye,
                    Color.Gray,
                    Color.LightGray,
                    Color.Gray,
                    Greye,
                    Color.Black
                )
            )
        )) {

        infoBar()

    }
}


@Composable
fun infoBar() {
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val infoBarHeight = height.value * 0.03
    val sidePadding = 16.dp
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp,end = sidePadding, start = sidePadding)
        .height(infoBarHeight.dp)
        ){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 15.sp,text = "Day Book", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

        }
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(infoBarHeight.dp)
        .padding(end = sidePadding, start = sidePadding)){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 15.sp,text = "Sn.", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

            // date
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "Date", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }
        }
    }
}