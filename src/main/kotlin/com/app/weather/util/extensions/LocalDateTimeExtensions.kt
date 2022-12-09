package com.app.weather.util.extensions

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun tomorrow(): Pair<LocalDateTime, LocalDateTime> = tomorrowFirstSecond() to tomorrowLastSecond()

private fun tomorrowFirstSecond(): LocalDateTime = LocalDateTime.now()
    .plusDays(1)
    .withHour(0)
    .withMinute(0)
    .withSecond(0)

private fun tomorrowLastSecond(): LocalDateTime = LocalDateTime.now()
    .plusDays(1)
    .withHour(23)
    .withMinute(59)
    .withSecond(59)

fun fiveDaysFromNow(): LocalDateTime = LocalDateTime.now().plusDays(5).withHour(23).withMinute(59).withSecond(59)

fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.systemDefault()).toInstant())

fun LocalDateTime.isTomorrow() = toLocalDate().plusDays(1) == tomorrowFirstSecond().toLocalDate()

fun LocalDateTime.isFiveDaysFromNow() = toLocalDate().plusDays(5) == fiveDaysFromNow().toLocalDate()