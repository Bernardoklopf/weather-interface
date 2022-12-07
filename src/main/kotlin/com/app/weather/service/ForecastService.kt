package com.app.weather.service

import com.app.weather.dto.TemperatureDto
import com.app.weather.dto.toTemperatureDto
import com.app.weather.vo.TemperatureVo
import com.app.weather.entity.Temperature
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ForecastService(
    private val temperatureService: TemperatureService,
    private val cityService: CityService
) {

    fun getSummary(unit: String, desiredTemperature: Int, citiesIds: List<Int>): List<Int> =
        citiesIds.filter { cityId ->
            cityService.findOrCreateCity(cityId).let { city ->
                temperatureService.findOrRequestTemperatureForTomorrow(city)
            }.hasAnyTemperatureAbove(desiredTemperature)
        }

    private fun List<Temperature>.hasAnyTemperatureAbove(temperature: Int) = any {
        it.temp_min >= BigDecimal(temperature.toString())
    }

    fun getForecast(cityId: Int): List<TemperatureDto> =
        cityService.findOrCreateCity(cityId).let { city ->
            temperatureService.findOrRequestTemperatureNextFiveDays(city).map {
                it.toTemperatureDto()
            }
        }
}
