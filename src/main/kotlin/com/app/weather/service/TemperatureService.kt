package com.app.weather.service

import com.app.weather.component.OpenWeatherForecastComponent
import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import com.app.weather.repository.TemperatureRepository
import com.app.weather.util.extensions.*
import com.app.weather.vo.ForecastVo
import org.springframework.stereotype.Service

@Service
class TemperatureService(
    private val temperatureRepository: TemperatureRepository,
    private val openWeatherForecastComponent: OpenWeatherForecastComponent
) {

    fun findOrRequestTemperatureForTomorrow(city: City) = getTemperatureForTomorrow(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city))
            .filter {
                it.date.belongsToTomorrow()
            }

    fun findOrRequestTemperatureNextFiveDays(city: City) = getTemperatureForNextFiveDays(city)
        ?: saveForecastAsTemperature(openWeatherForecastComponent.getForecast(city))

    private fun getTemperatureForTomorrow(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            tomorrow().second.toDate()
        ).ifEmpty { null }

    private fun getTemperatureForNextFiveDays(city: City): List<Temperature>? = temperatureRepository
        .findAllByCityAndDateBetween(
            city,
            tomorrow().first.toDate(),
            fiveDaysFromNow().toDate()
        ).filter {
            it.date.belongsToFiveDaysFromNoww()
        }.ifEmpty { null }

    private fun saveForecastAsTemperature(forecast: ForecastVo): List<Temperature> = temperatureRepository
        .saveAll(forecast.toListTemperature()).toList()
}