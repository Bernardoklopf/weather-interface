package com.app.weather.service

import com.app.weather.component.OpenWeatherForecastComponent
import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import com.app.weather.repository.TemperatureRepository
import com.app.weather.util.extensions.*
import com.app.weather.vo.ForecastVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TemperatureService(
    private val temperatureRepository: TemperatureRepository,
    private val openWeatherForecastComponent: OpenWeatherForecastComponent
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun findOrRequestTemperatureForTomorrow(city: City): List<Temperature> = getTemperatureForTomorrowOrNull(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city))
            .filter {
                it.date.belongsToTomorrow()
            }.also { logger.info("Temperature for tomorrow doesn't exist. Resquesting for city $city") }

    fun findOrRequestTemperatureNextFiveDays(city: City): List<Temperature> = getTemperatureForNextFiveDaysOrNull(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city)).also {
            logger.info("Temperature for next five full days doesn't exist. Resquesting for city $city")
        }

    private fun getTemperatureForTomorrowOrNull(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            tomorrow().second.toDate()
        ).ifEmpty { null }
        .also {
            logger.info("Finding temperature for tomorrow for city id = $city")
        }

    private fun getTemperatureForNextFiveDaysOrNull(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            fiveDaysFromNowEndOfTheDay().toDate()
        ).existTemperatureForNextFiveDaysOrNull()
        .also {
            logger.info("Finding temperature for next five days for city id = $city")
        }

    private fun List<Temperature>.existTemperatureForNextFiveDaysOrNull() = let { list ->
        if (list.any { temp ->
                temp.date.belongsToFiveDaysFromNow()
            }
        ) list
        else null
    }

    private fun saveForecastAsTemperature(forecast: ForecastVo): List<Temperature> =
        temperatureRepository.saveAll(forecast.toListTemperature()).toList()
}