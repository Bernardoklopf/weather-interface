package com.app.weather.entity.audit.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"])
abstract class AuditModel : Serializable {

//    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    open var createdAt: Date? = null

//    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    open var updatedAt: Date? = null

}
