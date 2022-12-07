package com.app.weather.entity

import com.app.weather.util.extensions.normalized
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.io.Serializable
import java.math.BigDecimal

@Entity
open class City(

    @Id
    @Column(name = "id", nullable = false)
    val id: Int = 0,

    val name: String = "",

    val state: String = "",

    val country: String = "",

    val lat: BigDecimal = BigDecimal.ZERO,

    val lon: BigDecimal = BigDecimal.ZERO,

    val numberOfRequests: Int = 1,

    val cityNotFound: Boolean = false

): Serializable