package com.app.weather.dto

import com.app.weather.entity.Temperature
import com.app.weather.util.extensions.toLocalDateTime
import java.math.BigDecimal

data class TemperatureDto(

    val date: String,

    val temp: BigDecimal,

    val feels_like: BigDecimal,

    val temp_max: BigDecimal,

    val temp_min: BigDecimal
)

fun Temperature.toTemperatureDto() = TemperatureDto(
    date = date.toLocalDateTime().toString(),
    temp = temp,
    feels_like = feels_like,
    temp_max = temp_max,
    temp_min = temp_min
)
