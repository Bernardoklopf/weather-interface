package com.app.weather.exceptions

class InvalidCityCodeException(id: String): RuntimeException("City code $id has already been looked up and does not exist.")