package com.app.weather.service

import com.app.weather.dto.TemperatureDto
import com.app.weather.dto.toTemperatureDto
import com.app.weather.entity.Temperature
import com.app.weather.enums.UnitType.METRIC
import com.app.weather.enums.toUnitType
import com.app.weather.exceptions.CityNotFoundException
import com.app.weather.util.extensions.convert
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ForecastService(
    private val temperatureService: TemperatureService,
    private val cityService: CityService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getSummary(unit: String, desiredTemperature: Int, citiesIds: List<Int>): List<Int> =
        citiesIds.filter { cityId ->
            cityService.findOrCreateCity(cityId).let { city ->
                try {
                    temperatureService.findOrRequestTemperatureForTomorrow(city).also {
                        cityService.updateAndIncrementCityRequest(city)
                    }
                } catch (e: CityNotFoundException) {
                    cityService.setCityNotFoundFlag(city)
                    logger.warn("City not found: $city")
                    return emptyList()
                }
            }.hasAnyTemperatureAbove(
                BigDecimal(desiredTemperature).convert(from = unit.toUnitType(), to = METRIC)
            )
        }

    private fun List<Temperature>.hasAnyTemperatureAbove(temperature: BigDecimal) = any {
        it.temp_min >= BigDecimal(temperature.toString())
    }

    fun getForecast(cityId: Int, unit: String?): List<TemperatureDto> =
        cityService.findOrCreateCity(cityId).let { city ->
            temperatureService.findOrRequestTemperatureNextFiveDays(city).map {
                it.toTemperatureDto(unit.toUnitType())
            }
        }
}
