package com.app.weather.rest

import com.app.weather.service.ForecastService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("weather")
class WeatherRest(private val forecastService: ForecastService) {

    @GetMapping("summary")
    fun getSummary(
        @RequestParam("unit") unit: String = "celsius",
        @RequestParam("temperature") temperature: Int,
        @RequestParam("locations") locations: List<Int>
    ) = forecastService.getSummary(unit, temperature, locations)

    @GetMapping("locations/{location_id}")
    fun getForecast(
        @PathVariable("location_id") location: Int
    ) = forecastService.getForecast(location)
}