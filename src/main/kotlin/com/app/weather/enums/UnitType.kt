package com.app.weather.enums

import com.app.weather.exceptions.UnitNotFoundException

enum class UnitType(val queryParam: String) {
    METRIC("metric"),
    IMPERIAL("imperial")
}

fun String.toUnitType() =
    when(this){
        "celsius" -> UnitType.METRIC
        "fahrenheit" -> UnitType.IMPERIAL
        else -> throw UnitNotFoundException(this)
    }
