package com.app.weather.extensions

import com.app.weather.util.extensions.belongsToFiveDaysFromNow
import com.app.weather.util.extensions.belongsToTomorrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class DateExtensionsTest {

    @Nested
    inner class BelongsToTomorrow{

        @Test
        fun `true`(){

            val calendar = Calendar.getInstance()

            calendar.time = Date()

            calendar.add(Calendar.DATE, 1)

            val tomorrow = calendar.time

            assertTrue(tomorrow.belongsToTomorrow())
        }

        @Test
        fun today(){

            val today = Date()

            assertFalse(today.belongsToTomorrow())
        }

        @Test
        fun `two days from now`(){

            val calendar = Calendar.getInstance()

            calendar.time = Date()

            calendar.add(Calendar.DATE, 2)

            val twoDaysFromNow = calendar.time

            assertFalse(twoDaysFromNow.belongsToTomorrow())
        }
    }

    @Nested
    inner class BelongsToFiveDaysFromNow{

        @Test
        fun `true`(){

            val calendar = Calendar.getInstance()

            calendar.time = Date()

            calendar.add(Calendar.DATE, 5)

            val fiveDaysFromNow = calendar.time

            assertTrue(fiveDaysFromNow.belongsToFiveDaysFromNow())
        }

        @Test
        fun `today = false`(){

            val today = Date()

            assertFalse(today.belongsToFiveDaysFromNow())
        }

        @Test
        fun `six days from now = false`(){

            val calendar = Calendar.getInstance()

            calendar.time = Date()

            calendar.add(Calendar.DATE, 6)

            val twoDaysFromNow = calendar.time

            assertFalse(twoDaysFromNow.belongsToFiveDaysFromNow())
        }
    }
}