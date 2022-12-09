package com.app.weather.templates

import com.app.weather.util.extensions.toLocalDateTime
import com.app.weather.vo.*
import java.math.BigDecimal


fun forecastVoTemplate() = ForecastVo(
    city = CityVo(
        id = 3448439,
        name = "São Paulo",
        coord = CoordinatesVo(BigDecimal("-23.5475"), BigDecimal("-46.6361")),
        country = "BR"
    ),
    list = listOf(
        ListWeatherVo(
            dt_txt = "2022-12-06 03:00:00".toLocalDateTime(),
            main = TemperatureVo(
                temp = BigDecimal("20.75"),
                feels_like = BigDecimal("21.34"),
                temp_max = BigDecimal("20.75"),
                temp_min = BigDecimal("19.36")
            )
        ),
        ListWeatherVo(
            dt_txt = "2022-12-06 06:00:00".toLocalDateTime(),
            main = TemperatureVo(
                temp = BigDecimal("20.22"),
                feels_like = BigDecimal("20.75"),
                temp_max = BigDecimal("20.22"),
                temp_min = BigDecimal("19.16")
            )
        ),
        ListWeatherVo(
            dt_txt = "2022-12-06 09:00:00".toLocalDateTime(),
            main = TemperatureVo(
                temp = BigDecimal("19.72"),
                feels_like = BigDecimal("20.2"),
                temp_max = BigDecimal("19.72"),
                temp_min = BigDecimal("19.2")
            )
        ),
        ListWeatherVo(
            dt_txt = "2022-12-07 03:00:00".toLocalDateTime(),
            main = TemperatureVo(
                temp = BigDecimal("19.28"),
                feels_like = BigDecimal("19.82"),
                temp_max = BigDecimal("19.28"),
                temp_min = BigDecimal("19.28")
            )
        )
    ),
    cod = "200",
    message = 0,
    cnt = 40
)

fun forecastJsonTemplate() = """
    {
        "cod": "200",
        "message": 0,
        "cnt": 40,
        "list": [
            {
                "dt": 1670295600,
                "main": {
                    "temp": 20.75,
                    "feels_like": 21.34,
                    "temp_min": 19.36,
                    "temp_max": 20.75,
                    "pressure": 997,
                    "sea_level": 997,
                    "grnd_level": 928,
                    "humidity": 94,
                    "temp_kf": 1.39
                },
                "weather": [
                    {
                        "id": 500,
                        "main": "Rain",
                        "description": "light rain",
                        "icon": "10n"
                    }
                ],
                "clouds": {
                    "all": 75
                },
                "wind": {
                    "speed": 2.67,
                    "deg": 6,
                    "gust": 8.58
                },
                "visibility": 10000,
                "pop": 1,
                "rain": {
                    "3h": 2.1
                },
                "sys": {
                    "pod": "n"
                },
                "dt_txt": "2022-12-06 03:00:00"
            },
            {
                "dt": 1670306400,
                "main": {
                    "temp": 20.22,
                    "feels_like": 20.75,
                    "temp_min": 19.16,
                    "temp_max": 20.22,
                    "pressure": 1002,
                    "sea_level": 1002,
                    "grnd_level": 927,
                    "humidity": 94,
                    "temp_kf": 1.06
                },
                "weather": [
                    {
                        "id": 500,
                        "main": "Rain",
                        "description": "light rain",
                        "icon": "10n"
                    }
                ],
                "clouds": {
                    "all": 83
                },
                "wind": {
                    "speed": 2.84,
                    "deg": 354,
                    "gust": 9.69
                },
                "visibility": 10000,
                "pop": 1,
                "rain": {
                    "3h": 0.68
                },
                "sys": {
                    "pod": "n"
                },
                "dt_txt": "2022-12-06 06:00:00"
            },
            {
                "dt": 1670317200,
                "main": {
                    "temp": 19.72,
                    "feels_like": 20.2,
                    "temp_min": 19.2,
                    "temp_max": 19.72,
                    "pressure": 1008,
                    "sea_level": 1008,
                    "grnd_level": 928,
                    "humidity": 94,
                    "temp_kf": 0.52
                },
                "weather": [
                    {
                        "id": 500,
                        "main": "Rain",
                        "description": "light rain",
                        "icon": "10d"
                    }
                ],
                "clouds": {
                    "all": 92
                },
                "wind": {
                    "speed": 2.65,
                    "deg": 354,
                    "gust": 9.66
                },
                "visibility": 10000,
                "pop": 0.75,
                "rain": {
                    "3h": 0.83
                },
                "sys": {
                    "pod": "d"
                },
                "dt_txt": "2022-12-06 09:00:00"
            },
            {
                "dt": 1670382000,
                "main": {
                    "temp": 19.28,
                    "feels_like": 19.82,
                    "temp_min": 19.28,
                    "temp_max": 19.28,
                    "pressure": 1014,
                    "sea_level": 1014,
                    "grnd_level": 928,
                    "humidity": 98,
                    "temp_kf": 0
                },
                "weather": [
                    {
                        "id": 502,
                        "main": "Rain",
                        "description": "heavy intensity rain",
                        "icon": "10n"
                    }
                ],
                "clouds": {
                    "all": 100
                },
                "wind": {
                    "speed": 2.49,
                    "deg": 3,
                    "gust": 5.67
                },
                "visibility": 10000,
                "pop": 1,
                "rain": {
                    "3h": 23.06
                },
                "sys": {
                    "pod": "n"
                },
                "dt_txt": "2022-12-07 03:00:00"
            }
        ],
        "city": {
            "id": 3448439,
            "name": "São Paulo",
            "coord": {
                "lat": -23.5475,
                "lon": -46.6361
            },
            "country": "BR",
            "population": 0,
            "timezone": -10800,
            "sunrise": 1670227920,
            "sunset": 1670276559
        }
    }
""".trimIndent()