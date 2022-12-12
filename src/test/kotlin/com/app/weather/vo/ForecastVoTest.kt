package com.app.weather.vo

import com.app.weather.templates.forecastVoTemplate
import com.app.weather.templates.temperatureListTemplate
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals

class ForecastVoTest {

    @Test
    fun `toListTemperature Test`() {

        val temperatureList = forecastVoTemplate().toListTemperature()

        assertEquals("List of Temperatures",temperatureList, temperatureListTemplate())
    }
}