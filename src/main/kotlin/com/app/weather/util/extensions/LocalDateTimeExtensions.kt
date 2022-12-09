package com.app.weather.util.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun tomorrow(): Pair<LocalDateTime, LocalDateTime> = tomorrowBeginning() to tomorrowEnd()

private fun tomorrowBeginning(): LocalDateTime = LocalDateTime.now()
    .plusDays(1).beginningOfTheDay()


private fun tomorrowEnd(): LocalDateTime = LocalDateTime.now()
    .plusDays(1).endOfTheDay()

private fun LocalDateTime.endOfTheDay(): LocalDateTime = this
    .withHour(23)
    .withMinute(59)
    .withSecond(59)
    .withNano(999999999)

private fun LocalDateTime.beginningOfTheDay(): LocalDateTime = this
    .withHour(0)
    .withMinute(0)
    .withSecond(0)
    .withNano(0)

fun fiveDaysFromNowEndOfTheDay(): LocalDateTime = LocalDateTime.now().plusDays(5).endOfTheDay()

fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.systemDefault()).toInstant())

fun LocalDateTime.isTomorrow() = toLocalDate() == LocalDate.now().plusDays(1)

fun LocalDateTime.isFiveDaysFromNow() = toLocalDate() == LocalDate.now().plusDays(5)