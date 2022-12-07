package com.app.weather.extensions

import com.app.weather.vo.ForecastVo
import com.app.weather.vo.WeatherVo
import com.app.weather.templates.forecastDtoTemplate
import com.app.weather.templates.forecastJsonTemplate
import com.app.weather.templates.weatherDtoTemplate
import com.app.weather.templates.weatherJsonTemplate
import com.app.weather.util.extensions.parseJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JsonExtensionsTest {

    @BeforeEach
    fun setup(){
//        mockkObject
    }

    @AfterEach
    fun teardown(){
//        unmockAll()
    }

    @Test
    fun `Testing json to WeatherDto conversion`(){
        assertThat(weatherJsonTemplate().parseJson(WeatherVo::class)).isEqualTo(weatherDtoTemplate())
    }

    @Test
    fun `Testing json to ForecastDto conversion`(){
        assertThat(forecastJsonTemplate().parseJson(ForecastVo::class)).isEqualTo(forecastDtoTemplate())
    }
}