package com.app.weather.util.extensions

import com.app.weather.util.deserializers.LocalDateTimeDeserializer
import com.google.gson.Gson
import java.time.LocalDateTime
import kotlin.reflect.KClass

fun <T : Any> String.parseJson(clasz: KClass<T>): T = Gson()
    .newBuilder()
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
    .create()
    .fromJson(this, clasz.java)
