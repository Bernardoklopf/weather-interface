package com.app.weather.vo

import java.math.BigDecimal

data class TemperatureVo(

    val temp: BigDecimal,

    val feels_like: BigDecimal,

    val temp_max: BigDecimal,

    val temp_min: BigDecimal
)
