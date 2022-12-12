package com.app.weather.templates

import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import com.app.weather.util.extensions.toDate
import java.math.BigDecimal
import java.time.LocalDateTime

fun temperatureListTemplate(
    city: City = cityTemplate(),
    startingTemperatureDate: LocalDateTime = LocalDateTime.of(2022, 12, 6, 3, 0)
) = listOf(
    Temperature(
        id = "3448439:$startingTemperatureDate",
        city = city,
        date = startingTemperatureDate.toDate(),
        temp = BigDecimal("20.75"),
        feels_like = BigDecimal("21.34"),
        temp_max = BigDecimal("20.75"),
        temp_min = BigDecimal("19.36")
    ),
    Temperature(
        id = "3448439:${startingTemperatureDate.plusDays(1)}",
        city = city,
        date = startingTemperatureDate.plusDays(1).toDate(),
        temp = BigDecimal("20.22"),
        feels_like = BigDecimal("20.75"),
        temp_max = BigDecimal("20.22"),
        temp_min = BigDecimal("19.16")
    ),
    Temperature(
        id = "3448439:${startingTemperatureDate.plusDays(2)}",
        city = city,
        date = startingTemperatureDate.plusDays(2).toDate(),
        temp = BigDecimal("19.72"),
        feels_like = BigDecimal("20.2"),
        temp_max = BigDecimal("19.72"),
        temp_min = BigDecimal("19.2")
    ),
    Temperature(
        id = "3448439:${startingTemperatureDate.plusDays(3)}",
        city = city,
        date = startingTemperatureDate.plusDays(3).toDate(),
        temp = BigDecimal("19.28"),
        feels_like = BigDecimal("19.82"),
        temp_max = BigDecimal("19.28"),
        temp_min = BigDecimal("19.28")
    ),
    Temperature(
        id = "3448439:${startingTemperatureDate.plusDays(4)}",
        city = city,
        date = startingTemperatureDate.plusDays(4).toDate(),
        temp = BigDecimal("19.28"),
        feels_like = BigDecimal("19.82"),
        temp_max = BigDecimal("19.28"),
        temp_min = BigDecimal("19.28")
    ),
    Temperature(
        id = "3448439:${startingTemperatureDate.plusDays(5)}",
        city = city,
        date = startingTemperatureDate.plusDays(5).toDate(),
        temp = BigDecimal("19.28"),
        feels_like = BigDecimal("19.82"),
        temp_max = BigDecimal("19.28"),
        temp_min = BigDecimal("19.28")
    )
)