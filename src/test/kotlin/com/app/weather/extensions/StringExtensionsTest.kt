package com.app.weather.extensions

import com.app.weather.util.extensions.toLocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class StringExtensionsTest {

    @Test
    fun toLocalDateTime(){
        val localDateTimeAsString = "2022-12-08 23:00:15"

        val localDateTime = LocalDateTime.of(2022, 12, 8, 23, 0,15 )

        assertThat(localDateTimeAsString.toLocalDateTime()).isEqualTo(localDateTime)
    }
}