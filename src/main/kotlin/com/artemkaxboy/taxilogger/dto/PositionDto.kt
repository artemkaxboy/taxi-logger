package com.artemkaxboy.taxilogger.dto

import com.artemkaxboy.taxilogger.entity.Position
import com.artemkaxboy.taxilogger.entity.Route
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Validated
data class PositionDto(

    val name: String,

    @field:Min(-90) @field:Max(90)
    val latitude: Double,

    @field:Min(-180) @field:Max(180)
    val longitude: Double,
)

fun Position.toDto() =
    PositionDto(name = name, latitude = latitude, longitude = longitude)
