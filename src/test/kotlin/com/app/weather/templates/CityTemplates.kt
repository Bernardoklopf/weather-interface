package com.app.weather.templates

import com.app.weather.entity.City
import java.math.BigDecimal

fun cityTemplate(
    id: Int = 3448439,
    name: String = "São Paulo",
    state: String = "",
    country: String = "BR",
    lat: BigDecimal = BigDecimal("-23.5506507"),
    lon: BigDecimal = BigDecimal("-46.6333824"),
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