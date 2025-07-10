package com.vky342.openerp.ui.screens.Analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AnalyticsScreens
import com.vky342.openerp.ui.theme.account_add_border_color
import com.vky342.openerp.ui.theme.account_add_content_color
import com.vky342.openerp.ui.theme.account_add_options_container_color
import com.vky342.openerp.ui.theme.account_add_shadow_color
import com.vky342.openerp.ui.theme.account_add_subtitle_color
import com.vky342.openerp.ui.theme.account_add_title_color
import com.vky342.openerp.ui.theme.background_color

@Composable
fun Analytics_home(navController: NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {
            ButtonCard(mainText = "Financial Overview", subText = "Display of cash analytics",onClick = {
                navController.navigate(AnalyticsScreens.financialOverview)
            })
            ButtonCard(icon = Icons.Default.DateRange,mainText = "Ledgers Overview", subText = "Pending Ledgers Display",onClick = {
                navController.navigate(AnalyticsScreens.ledgersOverview)
            })
        }
    }
}

@Composable
fun ButtonCard(modifier : Modifier = Modifier,
               onClick : () -> Unit = {},
               icon : ImageVector = Icons.Default.Create,
               mainText: String = "Title",
               subText: String = "Description",
               mainColor : Color = account_add_options_container_color
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20f),
                    ambientColor = account_add_shadow_color,
                    spotColor = account_add_shadow_color
                )
                .background(
                    color = mainColor,
                    shape = RoundedCornerShape(20f)
                )
                .border(1.dp, account_add_border_color, RoundedCornerShape(20f))
                .align(Alignment.Center)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(3.dp),
                    tint = account_add_content_color
                )
                Box (modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = mainText,
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = subText,
                        fontSize = 12.sp,
                        color = account_add_subtitle_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }
}