package com.app.weather.vo

import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import com.app.weather.templates.forecastVoTemplate
import com.app.weather.templates.temperatureListTemplate
import io.mockk.mockkConstructor
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals

class ForecastVoTest {

    @Test
    fun `toListTemperature Test`() {

        mockkConstructor(Temperature::class)
        mockkConstructor(City::class)

        val temperatureList = forecastVoTemplate().toListTemperature()

        assertEquals("List of Temperatures",temperatureList, temperatureListTemplate())
    }
}