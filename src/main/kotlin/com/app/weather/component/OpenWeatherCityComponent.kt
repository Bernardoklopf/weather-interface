package com.app.weather.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class OpenWeatherCityComponent {

    @Value("#{ @environment['openWeather.baseUrl'] }")
    private val openWeatherBaseUrl: String = ""

    @Value("#{ @environment['openWeather.appId'] }")
    private val appId: String = ""

//    fun getCityCode(@QueryParam("q") city: String)

}