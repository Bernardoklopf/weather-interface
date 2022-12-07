package com.app.weather.entity

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.util.Date

@Entity
open class Temperature(

    @Id
    val id: String = "",

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    val city: City = City(),

    val date: Date = Date(),

    val temp: BigDecimal = BigDecimal.ZERO,

    val feels_like: BigDecimal = BigDecimal.ZERO,

    val temp_max: BigDecimal = BigDecimal.ZERO,

    val temp_min: BigDecimal = BigDecimal.ZERO

): Serializable {
}