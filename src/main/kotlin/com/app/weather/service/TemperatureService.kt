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

    fun findOrRequestTemperatureForTomorrow(city: City) = getTemperatureForTomorrow(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city))
            .filter {
                it.date.belongsToTomorrow()
            }.also { logger.debug("Temperature for tomorrow doesn't exist. Resquesting for city $city") }

    fun findOrRequestTemperatureNextFiveDays(city: City) = getTemperatureForNextFiveDays(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city)).also {
            logger.debug("Temperature for next five full days doesn't exist. Resquesting for city $city")
        }

    private fun getTemperatureForTomorrow(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            tomorrow().second.toDate()
        ).ifEmpty { null }
        .also {
            logger.debug("Finding temperature for tomorrow for city id = $city")
        }

    private fun getTemperatureForNextFiveDays(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            fiveDaysFromNowEndOfTheDay().toDate()
        ).filter {
            it.date.belongsToFiveDaysFromNow()
        }.ifEmpty { null }
        .also {
            logger.debug("Finding temperature for next five days for city id = $city")
        }

    private fun saveForecastAsTemperature(forecast: ForecastVo): List<Temperature> =
        temperatureRepository.saveAll(forecast.toListTemperature()).toList()
}