package com.artemkaxboy.taxilogger.metric

import com.artemkaxboy.taxilogger.entity.RideParams
import com.artemkaxboy.taxilogger.service.RouteService
import com.artemkaxboy.taxilogger.service.YandexService
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StatusMetricsRegistry(

    private val meterRegistry: MeterRegistry,
    private val yandexService: YandexService,
    private val routeService: RouteService,
) {

    fun registerMeters(rideParams: RideParams) {

        Gauge
            .builder(RIDE_PRICE_METER) { yandexService.getRideDetails(rideParams)?.price?.amount }
            .tags(ROUTE_TAG, rideParams.route.name)
            .register(meterRegistry)

        Gauge
            .builder(RIDE_DISTANCE_METER) { yandexService.getRideDetails(rideParams)?.routeDetails?.distance }
            .tags(ROUTE_TAG, rideParams.route.name)
            .register(meterRegistry)

        Gauge
            .builder(RIDE_DURATION_METER) { yandexService.getRideDetails(rideParams)?.routeDetails?.duration?.toSeconds() }
            .tags(ROUTE_TAG, rideParams.route.name)
            .register(meterRegistry)

        Gauge
            .builder(RIDE_WAITING_TIME_METER) { yandexService.getRideDetails(rideParams)?.routeDetails?.waiting?.toSeconds() }
            .tags(ROUTE_TAG, rideParams.route.name)
            .register(meterRegistry)

        Gauge
            .builder(RIDE_SPEED_METER) { yandexService.getRideDetails(rideParams)?.routeDetails?.speed }
            .tags(ROUTE_TAG, rideParams.route.name)
            .register(meterRegistry)
    }

    @EventListener(ApplicationReadyEvent::class)
    fun initMeterRegistry() {

        routeService.getRoutes().forEach { route ->
            registerMeters(RideParams(route))
        }
    }
}

const val RIDE_PRICE_METER = "ride_price"
const val RIDE_DISTANCE_METER = "ride_distance"
const val RIDE_DURATION_METER = "ride_duration"
const val RIDE_WAITING_TIME_METER = "ride_waiting_time"
const val RIDE_SPEED_METER = "ride_speed"

const val ROUTE_TAG = "route"
