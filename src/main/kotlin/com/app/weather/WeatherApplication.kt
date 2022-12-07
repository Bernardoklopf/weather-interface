package com.app.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class WeatherApplication

fun main(args: Array<String>) {
	runApplication<WeatherApplication>(*args)
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
}
