package com.app.weather.vo

data class WeatherVo(

    val id: Int,

    val name: String,

    val coord: CoordinatesVo,

    val main: TemperatureVo,

    val cod: Int
)




