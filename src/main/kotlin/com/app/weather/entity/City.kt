package com.app.weather.entity

import com.app.weather.entity.audit.model.AuditModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Version
import java.math.BigDecimal

@Entity
data class City(

    @Id
    @Column(name = "id", nullable = false)
    val id: Int = 0,

    val name: String = "",

    val state: String = "",

    val country: String = "",

    val lat: BigDecimal = BigDecimal.ZERO,

    val lon: BigDecimal = BigDecimal.ZERO,

    val cityNotFound: Boolean = false,

    @Version
    val version: Long = 0

): AuditModel()