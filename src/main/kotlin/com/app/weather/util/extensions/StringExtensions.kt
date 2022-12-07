package com.app.weather.util.extensions

import java.text.Normalizer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val diacriticsCharGroupRegex = "\\p{Mn}+"

private const val notWordOrWhitespaceRegex = "[^\\w\\s]"

fun String.removeSpecialChars(): String = Normalizer
    .normalize(this, Normalizer.Form.NFD)
    .replace(diacriticsCharGroupRegex.toRegex(), "")

fun String.removeNotAlfaNumericKeepingSpaces(): String = this.replace(notWordOrWhitespaceRegex.toRegex(), "")

fun String.normalized(): String = this.removeSpecialChars().toLowerCase().removeNotAlfaNumericKeepingSpaces()

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))