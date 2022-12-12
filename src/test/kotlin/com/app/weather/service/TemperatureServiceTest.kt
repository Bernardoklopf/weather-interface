package com.app.weather.service

import com.app.weather.component.OpenWeatherForecastComponent
import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import com.app.weather.repository.TemperatureRepository
import com.app.weather.templates.cityTemplate
import com.app.weather.templates.forecastVoTemplate
import com.app.weather.templates.temperatureListTemplate
import com.app.weather.util.extensions.fiveDaysFromNowEndOfTheDay
import com.app.weather.util.extensions.toDate
import com.app.weather.util.extensions.tomorrow
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TemperatureServiceTest {

    private lateinit var temperatureRepository: TemperatureRepository

    private lateinit var openWeatherForecastComponent: OpenWeatherForecastComponent

    private lateinit var temperatureService: TemperatureService

    private val forecastVoTemplate = forecastVoTemplate()

    private val city = cityTemplate()


    @BeforeEach
    fun setup() {
        temperatureRepository = mockk()
        openWeatherForecastComponent = mockk()

        temperatureService = TemperatureService(temperatureRepository, openWeatherForecastComponent)

        mockkConstructor(Temperature::class)
        mockkConstructor(City::class)

        every { openWeatherForecastComponent.getForecast(city) } returns forecastVoTemplate
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }

    @Nested
    inner class FindOrRequestTemperatureForTomorrow {

        @BeforeEach
        fun setup(){
            every {
                temperatureRepository.findAllByCityAndDateBetween(
                    city,
                    tomorrow().first.toDate(),
                    tomorrow().second.toDate()
                )
            } returns emptyList()
        }

        @Test
        fun `if temperature is saved on db shouldn't call the api`() {
            every {
                temperatureRepository.findAllByCityAndDateBetween(
                    city,
                    tomorrow().first.toDate(),
                    tomorrow().second.toDate()
                )
            } returns listOf(Temperature())

            temperatureService.findOrRequestTemperatureForTomorrow(city)

            verify(exactly = 0) { openWeatherForecastComponent.getForecast(city) }
        }

        @Test
        fun `if temperature doesn't existis on db should call the api and save response`() {

            every { temperatureRepository.saveAll(any<List<Temperature>>()) } returns emptyList()

            temperatureService.findOrRequestTemperatureForTomorrow(city)

            verify(exactly = 1) { openWeatherForecastComponent.getForecast(city) }
            verify(exactly = 1) { temperatureRepository.saveAll(any<List<Temperature>>()) }
        }

        @Test
        fun `when calling the api should filter only temperatures that corresponds to tomorrow`() {
            val temperatureList = temperatureListTemplate(startingTemperatureDate = LocalDateTime.now().withHour(3))

            every { temperatureRepository.saveAll(any<List<Temperature>>()) } returns temperatureList

            val result = temperatureService.findOrRequestTemperatureForTomorrow(city)

            assertThat(result.size).isEqualTo(1)
            assertThat(result.first()).isEqualTo(temperatureList[1])
        }
    }

    @Nested
    inner class FndOrRequestTemperatureNextFiveDays {

        @BeforeEach
        fun setup(){
            every {
                temperatureRepository.findAllByCityAndDateBetween(
                    city,
                    tomorrow().first.toDate(),
                    fiveDaysFromNowEndOfTheDay().toDate()
                )
            } returns emptyList()
        }

        @Test
        fun `if temperature for the next five days is saved on db shouldn't call the api`() {
            every {
                temperatureRepository.findAllByCityAndDateBetween(
                    city,
                    tomorrow().first.toDate(),
                    fiveDaysFromNowEndOfTheDay().toDate()
                )
            } returns temperatureListTemplate(startingTemperatureDate = LocalDateTime.now())

            temperatureService.findOrRequestTemperatureNextFiveDays(city)

            verify(exactly = 0) { openWeatherForecastComponent.getForecast(city) }
        }

        @Test
        fun `if there is no temp for the fifth day should call the api`() {
            every {
                temperatureRepository.findAllByCityAndDateBetween(
                    city,
                    tomorrow().first.toDate(),
                    fiveDaysFromNowEndOfTheDay().toDate()
                )
            } returns temperatureListTemplate(startingTemperatureDate = LocalDateTime.now().minusDays(1))

            every { temperatureRepository.saveAll(any<List<Temperature>>()) } returns emptyList()

            temperatureService.findOrRequestTemperatureNextFiveDays(city)

            verify(exactly = 1) { openWeatherForecastComponent.getForecast(city) }
        }

        @Test
        fun `if there is no temp at all should call the api`() {

            every { temperatureRepository.saveAll(any<List<Temperature>>()) } returns emptyList()

            temperatureService.findOrRequestTemperatureNextFiveDays(city)

            verify(exactly = 1) { openWeatherForecastComponent.getForecast(city) }
        }
    }
}