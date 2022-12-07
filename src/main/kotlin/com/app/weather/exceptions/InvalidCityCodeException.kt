package com.app.weather.exceptions

class InvalidCityCodeException(id: String): RuntimeException("City code $id does not exist.")