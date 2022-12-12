package com.app.weather.component

import com.app.weather.entity.City
import com.app.weather.enums.UnitType
import com.app.weather.util.extensions.parseJson
import com.app.weather.vo.ForecastVo
import com.app.weather.vo.WeatherVo
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OpenWeatherForecastComponent {

    private val client = OkHttpClient().newBuilder().build()

    @Value("#{ @environment['openWeather.baseUrl'] }")
    private val openWeatherBaseUrl: String = ""

    @Value("#{ @environment['openWeather.appId'] }")
    private val appId: String = ""

    fun getForecast(
        city: City
    ): ForecastVo {

        val url = buildUrl(type = "forecast", city = city)

        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use {
            it.body!!.string().parseJson(ForecastVo::class)
        }
        //TODO: Incremet the counter of the city +1
        //TODO: Threat if the city was not found. Set the flag `cityNotFound` of the city, to true
    }

    fun getWeather(
        city: City
    ): WeatherVo {

        val url = buildUrl(type = "weather", city = city)

        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use {
            it.body!!.string().parseJson(WeatherVo::class)
        }
    }

    private fun buildUrl(
        type: String,
        city: City,
        unit: UnitType? = UnitType.METRIC
    ) = "${openWeatherBaseUrl}/data/2.5/$type?" +
            city.let { "id=${it.id}&" } +
            unit?.let { "units=${it.queryParam}&" }.orEmpty() +
            "appid=${appId}"
}