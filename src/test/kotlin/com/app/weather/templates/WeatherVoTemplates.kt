package com.app.weather.templates

import com.app.weather.vo.CoordinatesVo
import com.app.weather.vo.TemperatureVo
import com.app.weather.vo.WeatherVo
import java.math.BigDecimal

fun weatherVoTemplate() = WeatherVo(
    id = 3448439,
    coord = CoordinatesVo(BigDecimal("-23.5475"), BigDecimal("-46.6361")),
    main = TemperatureVo(
        temp = BigDecimal("23.16"),
        feels_like = BigDecimal("23.44"),
        temp_min = BigDecimal("20.94"),
        temp_max = BigDecimal("24.2")
    ),
    name = "São Paulo",
    cod = 200
)

fun weatherJsonTemplate() = """{
    "coord": {
        "lon": -46.6361,
        "lat": -23.5475
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 23.16,
        "feels_like": 23.44,
        "temp_min": 20.94,
        "temp_max": 24.2,
        "pressure": 995,
        "humidity": 73
    },
    "visibility": 10000,
    "wind": {
        "speed": 3.6,
        "deg": 70
    },
    "clouds": {
        "all": 0
    },
    "dt": 1669945810,
    "sys": {
        "type": 1,
        "id": 8394,
        "country": "BR",
        "sunrise": 1669882297,
        "sunset": 1669930795
    },
    "timezone": -10800,
    "id": 3448439,
    "name": "São Paulo",
    "cod": 200
}""".trimIndent()