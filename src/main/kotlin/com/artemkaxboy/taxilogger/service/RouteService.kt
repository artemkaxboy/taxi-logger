package com.artemkaxboy.taxilogger.service

import com.artemkaxboy.taxilogger.entity.Route
import com.artemkaxboy.taxilogger.repository.RouteRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class RouteService(
    private val routeRepository: RouteRepository,
) {

    @Suppress("unused") // Service function
    fun addRoute(route: Route) {
        routeRepository.save(route)
    }

    fun getRoutes(): List<Route> = routeRepository.findAll()
        .onEach { logger.debug { "Route loaded $it" } }
}
