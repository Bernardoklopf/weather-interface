package com.app.weather.exceptions

class ForecastMissingDataException(): RuntimeException("Response does not contain data for the next 5 days.")