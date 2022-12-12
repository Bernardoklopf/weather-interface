package com.app.weather.service

import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class ForecastServiceTest {

    private lateinit var temperatureService: TemperatureService

    private lateinit var cityService: CityService

    private lateinit var forecastService: ForecastService

    @BeforeEach
    fun setup() {
        temperatureService = mockk()
        cityService = mockk()

        forecastService = ForecastService(temperatureService, cityService)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Nested
    inner class GetSummary {

        @ParameterizedTest
        @CsvSource("-10, 3", "0, 3", "2, 3", "10, 2", "19, 1", "20, 1", "21, 0")
        fun `should return only the cities which have temperature above in celcius`(desiredTemp: Int, citiesAboveTemp: Int) {
            val citiesIds = listOf(
                1 to listOf(Temperature(temp_min = BigDecimal("2"))),
                2 to listOf(Temperature(temp_min = BigDecimal("10"))),
                3 to listOf(Temperature(temp_min = BigDecimal("20")))
            )

            citiesIds.forEach {
                every { cityService.findOrCreateCity(it.first) } returns City(it.first)
                every { temperatureService.findOrRequestTemperatureForTomorrow(City(it.first)) } returns it.second
            }

            val result = forecastService.getSummary("celsius", desiredTemp, citiesIds.map { it.first })

            assertThat(result.size).isEqualTo(citiesAboveTemp)
        }
    }

    @ParameterizedTest
    @CsvSource("14, 3", "32, 3", "35, 3", "49, 2", "66, 1", "67, 1", "69, 0")
    fun `should return only the cities which have temperature above in fahrenheit`(
        desiredTemp: Int,
        citiesAboveTemp: Int
    ) {
        val citiesIds = listOf(
            1 to listOf(Temperature(temp_min = BigDecimal("2"))),
            2 to listOf(Temperature(temp_min = BigDecimal("10"))),
            3 to listOf(Temperature(temp_min = BigDecimal("20")))
        )

        citiesIds.forEach {
            every { cityService.findOrCreateCity(it.first) } returns City(it.first)
            every { temperatureService.findOrRequestTemperatureForTomorrow(City(it.first)) } returns it.second
        }

        val result = forecastService.getSummary("fahrenheit", desiredTemp, citiesIds.map { it.first })

        assertThat(result.size).isEqualTo(citiesAboveTemp)
    }

    @Test
    fun `empty cityId list`(){
        val result = forecastService.getSummary("celsius", 20, emptyList())

        assertThat(result).isEmpty()
    }
}