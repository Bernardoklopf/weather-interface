package com.app.weather.util.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime =
        LocalDateTime.parse(json.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}