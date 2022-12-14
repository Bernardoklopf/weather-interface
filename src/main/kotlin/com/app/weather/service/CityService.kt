package com.app.weather.service

import com.app.weather.entity.City
import com.app.weather.exceptions.InvalidCityCodeException
import com.app.weather.repository.CityRepository
import com.app.weather.vo.CityVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CityService(
    private val cityRepository: CityRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun findOrCreateCity(id: Int): City = (
            findByIdOrNull(id)?.also { logger.info("City already exists: $it") }
                ?: cityRepository.save(City(id = id))
                    .also { logger.info("City id = $id not found. Creating new city.") }
            ).also { if (it.cityNotFound) throw InvalidCityCodeException("$id") }

    fun findByIdOrNull(id: Int): City? = cityRepository.findById(id).orElse(null)

    fun setCityNotFoundFlagToTrue(city: City) = findByIdOrNull(city.id)?.let {
        cityRepository.save(
            it.copy(
                cityNotFound = true
            )
        )
    }

    fun incrementCityRequest(city: City) = findByIdOrNull(city.id)?.let {
        cityRepository.save(
            it.copy(
                numberOfRequests = it.numberOfRequests+1
            )
        )
    }

    fun updateCityInfo(city: City, cityVo: CityVo) = findByIdOrNull(city.id)?.let {
        cityRepository.save(
            it.copy(
                name = cityVo.name,
                country = cityVo.country,
                lat = cityVo.coord.lat,
                lon = cityVo.coord.lon,
                numberOfRequests = it.numberOfRequests+1
            )
        )
    }

}
