package com.app.weather.extensions

import com.app.weather.util.extensions.celciusToFahrenheit
import com.app.weather.util.extensions.fahrenheitToCelcius
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class BigDecimalExtensionsTest {

    @ParameterizedTest
    @CsvSource("32, 0.00", "-10, -23.33", "100.5, 38.06", "24.13, -4.37")
    fun `fahrenheit to celcius`(fahrenheit: String, celcius: String) {
        assertThat(BigDecimal(fahrenheit).fahrenheitToCelcius()).isEqualTo(BigDecimal(celcius))
    }

    @ParameterizedTest
    @CsvSource("32.00, 0", "-10.00, -23.33", "100.50, 38.06", "24.13, -4.37")
    fun `celcius to fahrenheit`(fahrenheit: String, celcius: String) {
        assertThat(BigDecimal(celcius).celciusToFahrenheit()).isEqualTo(BigDecimal(fahrenheit))
    }

}