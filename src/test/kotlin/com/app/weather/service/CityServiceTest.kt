package com.app.weather.service

import com.app.weather.entity.City
import com.app.weather.exceptions.InvalidCityCodeException
import com.app.weather.repository.CityRepository
import com.app.weather.templates.cityTemplate
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class CityServiceTest {

    private lateinit var cityRepository: CityRepository

    private lateinit var cityService: CityService

    private val city = cityTemplate()


    @BeforeEach
    fun setup() {
        cityRepository = mockk()
        cityService = CityService(cityRepository)
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }

    @Nested
    inner class FindOrCreateCity {

        @Test
        fun `find city that exists`() {

            every { cityRepository.findById(city.id) } returns Optional.of(city)

            val result = cityService.findOrCreateCity(city.id)

            assertThat(result).isEqualTo(city)
            verify(exactly = 0) { cityRepository.save(any()) }
        }

        @Test
        fun `find city that exists but has cityNotFound = true`() {

            val city = cityTemplate(cityNotFound = true)

            every { cityRepository.findById(city.id) } returns Optional.of(city)

            assertThrows<InvalidCityCodeException> { cityService.findOrCreateCity(city.id) }
            verify(exactly = 0) { cityRepository.save(any()) }
        }

        @Test
        fun `create city that doesn't exists yet`() {

            val city = City(id = 654321)

            every { cityRepository.findByIdOrNull(654321) } returns null

            val citySlot = slot<City>()

            every { cityRepository.save(capture(citySlot)) } returns city

            val result = cityService.findOrCreateCity(654321)

            assertThat(result).isEqualTo(city)
            assertThat(citySlot.captured.id).isEqualTo(city.id)
        }
    }

    @Nested
    inner class FindByIdOrNull {

        @Test
        fun `find city that exists`() {

            every { cityRepository.findById(city.id) } returns Optional.of(city)

            val result = cityService.findByIdOrNull(city.id)

            assertThat(result).isEqualTo(city)
        }

        @Test
        fun `should return null`() {

            every { cityRepository.findById(city.id) } returns Optional.empty()

            val result = cityService.findByIdOrNull(city.id)

            assertThat(result).isNull()
        }
    }
}