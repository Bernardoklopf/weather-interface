package com.app.weather.extensions

import com.app.weather.util.extensions.isFiveDaysFromNow
import com.app.weather.util.extensions.isTomorrow
import com.app.weather.util.extensions.tomorrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LocalDateTimeExtensionsTest {

    @Nested
    inner class Tomorrow {

        @Test
        fun beginning() {
            val tomorrowBeginning = LocalDateTime.now()
                .plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)

            assertThat(tomorrow().first).isEqualTo(tomorrowBeginning)
        }

        @Test
        fun end() {
            val tomorrowEnd = LocalDateTime.now()
                .plusDays(1)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999)

            assertThat(tomorrow().second).isEqualTo(tomorrowEnd)
        }
    }

    @Test
    fun fiveDaysFromNowEndOfTheDay() {
        val fiveDaysFromNow = LocalDateTime.now()
            .plusDays(5)
            .withHour(23)
            .withMinute(59)
            .withSecond(59)
            .withNano(999999999)

        assertThat(fiveDaysFromNow).isEqualTo(fiveDaysFromNowEndOfTheDay())
    }

    @Test
    fun isTomorrow(){
        val tomorrow = LocalDateTime.now().plusDays(1)

        assertThat(tomorrow.isTomorrow()).isTrue
    }

    @Test
    fun isFiveDaysFromNow(){
        val fivaDaysFromNow = LocalDateTime.now().plusDays(5)

        assertThat(fivaDaysFromNow.isFiveDaysFromNow()).isTrue
    }
}