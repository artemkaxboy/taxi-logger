package com.artemkaxboy.taxilogger.api

import com.artemkaxboy.taxilogger.dto.RouteDto
import com.artemkaxboy.taxilogger.dto.toDto
import com.artemkaxboy.taxilogger.service.RouteService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class RouteCrudController(private val routeService: RouteService) {

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation")
        ]
    )
    @GetMapping("/route", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRoutes(): ResponseEntity<Collection<RouteDto>> {
        return routeService.getRoutes().map { it.toDto() }
            .let { ResponseEntity.ok(it) }
    }
}
