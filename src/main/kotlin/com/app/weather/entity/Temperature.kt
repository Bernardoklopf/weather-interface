package com.app.weather.entity

import com.app.weather.entity.audit.model.AuditModel
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Temperature(

    @Id
    val id: String = "",

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    val city: City = City(),

    val date: Date = Date(),

    val temp: BigDecimal = BigDecimal.ZERO,

    val feels_like: BigDecimal = BigDecimal.ZERO,

    val temp_max: BigDecimal = BigDecimal.ZERO,

    val temp_min: BigDecimal = BigDecimal.ZERO,

    @Version
    val version: Long = 0

): AuditModel()