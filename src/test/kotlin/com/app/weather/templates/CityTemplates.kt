package com.app.weather.templates

import com.app.weather.entity.City
import java.math.BigDecimal

fun cityTemplate(
    id: Int = 123456,
    name: String = "Copenhagen",
    state: String = "Capital Region",
    country: String = "Denmark",
    lat: BigDecimal = BigDecimal("55.6867243"),
    lon: BigDecimal = BigDecimal("12.5700724"),
    numberOfRequests: Int = 1,
    cityNotFound: Boolean = false
) = City(
    id = id,
    name = name,
    state = state,
    country = country,
    lat = lat,
    lon = lon,
    numberOfRequests = numberOfRequests,
    cityNotFound = cityNotFound
)