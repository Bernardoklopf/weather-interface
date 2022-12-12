package com.app.weather.util.extensions

import com.app.weather.enums.UnitType
import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.fahrenheitToCelcius(): BigDecimal = this
    .minus(BigDecimal("32"))
    .times(BigDecimal("5.00").divide(BigDecimal("9"), 4, RoundingMode.HALF_UP))
    .setScale(2, RoundingMode.CEILING)

fun BigDecimal.celciusToFahrenheit(): BigDecimal = this
    .times(BigDecimal("9").divide(BigDecimal("5"), 3, RoundingMode.HALF_UP))
    .setScale(2, RoundingMode.FLOOR)
    .plus(BigDecimal("32"))

fun BigDecimal.convert(from: UnitType, to: UnitType): BigDecimal =
    when (from) {
        UnitType.METRIC -> when (to) {
            UnitType.IMPERIAL -> this.celciusToFahrenheit()
            UnitType.METRIC -> this
        }
        UnitType.IMPERIAL -> when (to) {
            UnitType.METRIC -> this.fahrenheitToCelcius()
            UnitType.IMPERIAL -> this
        }
    }