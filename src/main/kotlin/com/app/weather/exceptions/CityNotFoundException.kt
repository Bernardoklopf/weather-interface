package com.app.weather.exceptions

import com.app.weather.entity.City

class CityNotFoundException(city: City) : RuntimeException("City ${city.id} was not found.") {

}
