package com.app.weather.dto

import com.app.weather.entity.Temperature
import com.app.weather.enums.UnitType
import com.app.weather.enums.UnitType.METRIC
import com.app.weather.util.extensions.convert
import com.app.weather.util.extensions.toLocalDateTime
import java.math.BigDecimal

data class TemperatureDto(

    val date: String,

    val temp: BigDecimal,

    val feels_like: BigDecimal,

    val temp_max: BigDecimal,

    val temp_min: BigDecimal
)

fun Temperature.toTemperatureDto(unit: UnitType) = TemperatureDto(
    date = date.toLocalDateTime().toString(),
    temp = temp.convert(from = METRIC, to = unit),
    feels_like = feels_like.convert(from = METRIC, to = unit),
    temp_max = temp_max.convert(from = METRIC, to = unit),
    temp_min = temp_min.convert(from = METRIC, to = unit)
)
