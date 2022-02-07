package com.artemkaxboy.taxilogger.entity

import com.artemkaxboy.taxilogger.dto.RideYandexDto
import com.artemkaxboy.taxilogger.dto.RideOptionsYandexDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Duration
import java.time.LocalDateTime

data class Ride(

    val route: Route,
    val routeDetails: RouteDetails,
    val carClass: CarClass,
    val price: Price,
    val `when`: LocalDateTime = LocalDateTime.now(),
)

fun makeRideEntity(rideParams: RideParams, rideDto: RideYandexDto, optionsDto: RideOptionsYandexDto): Ride {
    return Ride(
        route = rideParams.route,
        routeDetails = RouteDetails(
            waiting = Duration.ofSeconds(optionsDto.waitingTime.toLong()),
            distance = rideDto.distance.toLong(),
            duration = Duration.ofSeconds(rideDto.time.toLong()),
        ),
        carClass = CarClass(optionsDto.className, optionsDto.classLevel),
        price = Price(amount = optionsDto.price, currency = rideDto.currency),
    )
}
