package com.app.weather.repository

import com.app.weather.entity.City
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : CrudRepository<City, Int> {


}