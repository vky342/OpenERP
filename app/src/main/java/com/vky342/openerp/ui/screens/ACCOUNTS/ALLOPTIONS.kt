package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AccountScreens
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.account_list_title_border_color
import com.vky342.openerp.ui.theme.account_list_title_color
import com.vky342.openerp.ui.theme.account_list_title_container_color
import com.vky342.openerp.ui.theme.account_screen_background_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun AllOptionsScreen(
    navController: NavHostController
) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    val sidePadding = width.value * 0.05

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

            add_account_button()
            edit_account_button()
            account_search_bar(modifier = Modifier.padding(horizontal = sidePadding.dp))
            account_list()
        }
    }
}

    @Composable
    fun new_card(
        navController: NavHostController,
        to_location: String,
        main_title: String,
        sub_title: String,
        side_icon: ImageVector
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(horizontal = 25.dp, vertical = 13.dp),
            shape = RectangleShape,
            onClick = {

                navController.navigate(to_location)

            },
            colors = CardDefaults.cardColors()
                .copy(contentColor = Color.Black, containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {

                Box(modifier = Modifier.wrapContentHeight().weight(0.12f)) {
                    Icon(
                        imageVector = side_icon,
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center).padding(top = 3.dp)
                    )
                }


                Box(modifier = Modifier.wrapContentHeight().weight(1f)) {
                    Column() {
                        Text(
                            text = main_title,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.Start).padding(top = 3.dp)
                        )

                        Text(
                            text = sub_title,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Start)
                                .padding(vertical = 1.dp, horizontal = 2.dp)
                        )

                    }
                }

            }
            Spacer(
                modifier = Modifier.fillMaxWidth(0.6f).height(0.2.dp)
                    .background(color = Color.Black)
                    .align(
                        Alignment.CenterHorizontally
                    )
            )
        }
    }
