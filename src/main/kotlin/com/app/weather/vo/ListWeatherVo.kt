package com.app.weather.vo

import java.time.LocalDateTime

data class ListWeatherVo(

    val dt_txt: LocalDateTime,

    val main: TemperatureVo
) {
    fun toTemperatureId(city: CityVo): String = "${city.id}:$dt_txt"
}
