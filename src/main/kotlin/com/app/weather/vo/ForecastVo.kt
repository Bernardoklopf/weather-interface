package com.app.weather.vo

import com.app.weather.entity.Temperature
import com.app.weather.util.extensions.toDate

data class ForecastVo(

    val city: CityVo,

    val list: List<ListWeatherVo>,

    val cod: String,

    val message: Int,

    val cnt: Int
) {
    fun toListTemperature(): List<Temperature> = list.map {
        Temperature(
            id = it.toTemperatureId(city),
            city = city.toCity(),
            date = it.dt_txt.toDate(),
            temp = it.main.temp,
            feels_like = it.main.feels_like,
            temp_min = it.main.temp_min,
            temp_max = it.main.temp_max
        )
    }
}

