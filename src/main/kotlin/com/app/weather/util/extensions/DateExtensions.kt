package com.app.weather.util.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun Date.belongsToTomorrow() = this.toLocalDateTime().isTomorrow()

fun Date.belongsToFiveDaysFromNoww() = this.toLocalDateTime().isFiveDaysFromNow()

fun Date.toLocalDateTime(): LocalDateTime {
    val instant = Instant.ofEpochMilli(this.time)
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}