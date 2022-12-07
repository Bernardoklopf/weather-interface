package com.app.weather.vo

import com.app.weather.entity.City

data class CityVo(
    val id: Int,

    val name: String,

    val coord: CoordinatesVo,

    val country: String
) {
    fun toCity() = City(
        id = id,
        name = name,
        country = country,
        lat = coord.lat,
        lon = coord.lon
    )
}
