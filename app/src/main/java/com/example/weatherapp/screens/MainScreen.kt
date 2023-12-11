package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ItemRowModel
import com.example.weatherapp.R
import com.example.weatherapp.Room.Hour
import com.example.weatherapp.ViewModel.MainViewModel
import com.example.weatherapp.ui.theme.ItemColumnModel



@Composable
fun MainScreen () {
    val viewModel: MainViewModel = viewModel()
    val currentData by viewModel.currentWeatherLiveData.observeAsState()
    val hoursData by viewModel.hoursLiveData.observeAsState()
    Column(
        modifier = Modifier
            .background(Color(0xFF00A6FF))
            .fillMaxSize()
    ) {
        currentData?.let { UpperTempInfo(it) }
        hoursData?.let { CardDayView(it) }
        CardWeekView()
    }
}



@Composable
fun UpperTempInfo(currentData: com.example.weatherapp.Room.CurrentData)
{
    Column(modifier = Modifier.fillMaxWidth().background(Color(0xFF00A6FF))) {
        currentData?.let {
            Text(

                text = "${currentData.temp_c} C",
                fontSize = 64.sp,
                modifier = Modifier.padding(start = 24.dp, top = 16.dp)
            )
            Text(
                text = "${currentData.condition}",
                modifier = Modifier.padding(start = 24.dp)
            )
            Text(
                text = "округ Академический",
                modifier = Modifier.padding(start = 24.dp, top = 16.dp)
            )
        }
    }
}


@Composable
fun CardDayView(hoursData: List<Hour>)
{
    Card (modifier = Modifier
        .padding(top = 32.dp,start = 8.dp, end = 8.dp)
    ){
        LazyRow(modifier = Modifier.background(Color(0xFF00A6FF))
            .fillMaxWidth()
        )
        {
            itemsIndexed(hoursData)
            {index, hour ->
                ItemRow(item = ItemRowModel(hour.time,hour.temp_c))
                Spacer(modifier = Modifier.padding(8.dp))}
        }
    }
}


@Composable
fun ItemRow(item: ItemRowModel)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item.time.substring(11))
        Text(text = item.temp_c.toString())
        Image(painter = painterResource(id = R.drawable.clouds ), contentDescription = "image" )
    }
}

@Composable
fun CardWeekView()
{
    Card(modifier = Modifier.padding(top = 32.dp,start = 8.dp, end = 8.dp),colors = CardDefaults.cardColors(
        colorResource(id = R.color.white)
    )) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth())
        {
            itemsIndexed(
                listOf(
                    ItemColumnModel("Сегодня","19","10","88%"),
                    ItemColumnModel("Четверг","19","10","88%"),
                    ItemColumnModel("Пятница","19","10","88%"),
                    ItemColumnModel("Суббота","19","10","88%"),
                    ItemColumnModel("Воскресенье","19","10","88%"),
                    ItemColumnModel("Понедельник","19","10","88%"),
                    ItemColumnModel("Вторник","19","10","88%"),
                )
            ){index, item -> ItemColumn(item = item)}
        }
    }
}

@Composable
fun ItemColumn(item: ItemColumnModel)
{
    Row {
        Column(modifier = Modifier.width(100.dp)) {
            Text(text = item.day )
        }
        Column {
            Row (modifier = Modifier.padding(start = 32.dp)){

                Image(
                    painter = painterResource(id = R.drawable.humidity),
                    contentDescription = "humidity"
                )
                Text(text = item.humidity, modifier = Modifier.padding(start = 5.dp))
                Image(
                    painter = painterResource(id = R.drawable.clouds), contentDescription = "image",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.clouds), contentDescription = "image",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(text = item.tempMax,modifier = Modifier.padding(start = 16.dp))
                Text(text = item.tempMin, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
