package com.vky342.openerp.ui.screens.ACCOUNTS

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.data.dataInterface.Account

@Preview
@Composable
fun ListAllAccountsScreen(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1
    val heightval = topPadding/2.5

    val AllAccount = listOf(
        Account(name = "kunal", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "rahul", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "satyam", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nini", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "kuchcs", address = "sesk", contact = 2412,235),
        Account("sbjvdb","shvbdj",214,2353),
        Account("shjvbdk","vbsdkvbs",3423,3423),
        Account("svjdb","kdfjn",12443,5968),
        Account(name = "sdvkl", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "sefnks", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "sjbsnvs", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nrjvdr", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "erghvkd", address = "sesk", contact = 2412,235),
        Account("vkjc","shvbdj",214,2353),
        Account("sefhie","vbsdkvbs",3423,3423),
        Account("wefnkw","kdfjn",12443,5968),

        Account(name = "kunal", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "rahul", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "satyam", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nini", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "kuchcs", address = "sesk", contact = 2412,235),
        Account("sbjvdb","shvbdj",214,2353),
        Account("shjvbdk","vbsdkvbs",3423,3423),
        Account("svjdb","kdfjn",12443,5968),
        Account(name = "sdvkl", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "sefnks", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "sjbsnvs", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nrjvdr", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "erghvkd", address = "sesk", contact = 2412,235),
        Account("vkjc","shvbdj",214,2353),
        Account("sefhie","vbsdkvbs",3423,3423),
        Account("wefnkw","kdfjn",12443,5968),

        Account(name = "kunal", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "rahul", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "satyam", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nini", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "kuchcs", address = "sesk", contact = 2412,235),
        Account("sbjvdb","shvbdj",214,2353),
        Account("shjvbdk","vbsdkvbs",3423,3423),
        Account("svjdb","kdfjn",12443,5968),
        Account(name = "sdvkl", address = "collony", contact = 23423, netBalance = 123),
        Account(name = "sefnks", address = "sakti", contact = 234234, netBalance = 1231),
        Account(name = "sjbsnvs", address = "sakti", contact = 2413, netBalance = 0),
        Account(name = "nrjvdr", address = "sai vihar", contact = 1242453, netBalance = 796),
        Account(name = "erghvkd", address = "sesk", contact = 2412,235),
        Account("vkjc","shvbdj",214,2353),
        Account("sefhie","vbsdkvbs",3423,3423),
        Account("wefnkw","kdfjn",12443,5968)
        )

    Column(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()
        .padding(top = topPadding.dp, bottom = topPadding.dp)
    ) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(heightval.dp)
            .background(color = Color.White)){
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 0.1f)
                .border(width = 1.dp, color = Color.Black)){ // index
                Text(text = "index", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(), fontSize = 11.sp)
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .25f)
                .border(width = 1.dp, color = Color.Black)){ // name

                Text(text = "name", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(), fontSize = 11.sp)

            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .3f)
                .border(width = 1.dp, color = Color.Black)){ // address

                Text(text = "address", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(), fontSize = 11.sp)

            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .2f)
                .border(width = 1.dp, color = Color.Black)){ // contact

                Text(text = "contact", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(), fontSize = 11.sp)

            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .15f)
                .border(width = 1.dp, color = Color.Black)){ // net balance

                Text(text = "balance", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(), fontSize = 11.sp)

            }

        }

        LazyColumn (modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            //.padding(top = topPadding.dp, bottom = topPadding.dp)
        ) {
            itemsIndexed(items = AllAccount){ index : Int, item: Account ->
                    AccountListItem(index = index, account = item)
            }

        }
    }


}

//@Preview
@Composable
fun AccountListItem(index : Int, account: Account){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val heightval2 = topPadding/2.5

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(heightval2.dp)
        .background(color = Color.White)){
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = 0.1f)
            .border(width = 1.dp, color = Color.Black)){ // index
            Text(text = index.toString(), modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(), fontSize = 11.sp)
        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .25f)
            .border(width = 1.dp, color = Color.Black)){ // name

            Text(text = account.name, modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(), fontSize = 11.sp)

        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .3f)
            .border(width = 1.dp, color = Color.Black)){ // address

            Text(text = account.address, modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(), fontSize = 11.sp)

        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .2f)
            .border(width = 1.dp, color = Color.Black)){ // contact

            Text(text = account.contact.toString(), modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(), fontSize = 11.sp)

        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .15f)
            .border(width = 1.dp, color = Color.Black)){ // net balance

            Text(text = account.netBalance.toString(), modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(), fontSize = 11.sp)

        }

    }
}