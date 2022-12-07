package com.app.weather.repository

import com.app.weather.entity.City
import com.app.weather.entity.Temperature
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface TemperatureRepository : CrudRepository<Temperature, String> {

    fun findAllByCityAndDateBetween(city: City, initialDate: Date, finalDate: Date): List<Temperature>
}