package com.app.weather.exceptions

import okhttp3.Request
import okhttp3.Response

class FailedToGetForecastException(request: Request, response: Response) :
    RuntimeException("Failed to get forecast. \nRequests: $request \nResponse: $response")
