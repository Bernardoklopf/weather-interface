package com.app.weather.exceptions

class UnitNotFoundException(unit: String): RuntimeException("$unit does not exist.") {
}