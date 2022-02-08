package com.artemkaxboy.taxilogger.dto

import com.artemkaxboy.taxilogger.entity.Position
import com.artemkaxboy.taxilogger.entity.Route
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for a route.")
data class RouteDto(
    val name: String,
    val from: PositionDto,
    val to: PositionDto,
)

fun Route.toDto() =
    RouteDto(name = name, from = from.toDto(), to = to.toDto())
