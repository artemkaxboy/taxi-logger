package com.artemkaxboy.taxilogger.service

import com.artemkaxboy.taxilogger.configuration.properties.YandexApiProperties
import com.artemkaxboy.taxilogger.dto.RideYandexDto
import com.artemkaxboy.taxilogger.entity.Ride
import com.artemkaxboy.taxilogger.entity.RideParams
import com.artemkaxboy.taxilogger.entity.makeRideEntity
import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

private val logger = KotlinLogging.logger {}

@Service
class YandexService(
    private val restTemplate: RestTemplate,
    private val yandexApiProperties: YandexApiProperties,
) {

    @Cacheable("ride")
    fun getRideDetails(rideParams: RideParams): Ride? {

        logger.info { "Get ride $rideParams" }

        val ride = fetchRide(rideParams) ?: return null
        val cheapest = getCheapestOption(ride) ?: return null

        return makeRideEntity(rideParams, ride, cheapest)
    }

    private fun fetchRide(rideParams: RideParams) =
        restTemplate.getForObject(
            yandexApiProperties.getUri(),
            RideYandexDto::class.java,
            yandexApiProperties.getParamsMap(rideParams.route),
        )

    private fun getCheapestOption(ride: RideYandexDto?) =
        ride?.options?.minByOrNull { it.price }
}
