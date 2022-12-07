package com.app.weather.service

import com.app.weather.entity.City
import com.app.weather.exceptions.InvalidCityCodeException
import com.app.weather.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(
    private val cityRepository: CityRepository
) {
    fun findOrCreateCity(id: Int): City = (findByIdOrNull(id) ?: cityRepository.save(City(id = id))).also {
        if (it.cityNotFound) throw InvalidCityCodeException("$id")
    }

    fun findByIdOrNull(id: Int): City? = cityRepository.findById(id).orElse(null)
}