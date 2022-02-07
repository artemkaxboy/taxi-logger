package com.artemkaxboy.taxilogger.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Route(

    @Id
    val id: String? = null,
    val from: Position,
    val to: Position,
) {

    val name: String
        get() = "${from.name}-${to.name}"
}
