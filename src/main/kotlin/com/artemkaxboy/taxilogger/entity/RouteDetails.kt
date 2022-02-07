package com.artemkaxboy.taxilogger.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Duration

/**
 * Concrete route details, consider current traffic
 */
@Document
data class RouteDetails(

    /** Time to provide a car */
    val waiting: Duration,

    /** Traffic-aware distance in meters */
    val distance: Long,

    /** Traffic-aware estimated ride time in seconds */
    val duration: Duration,
) {

    val speed
        get() = distance.toDouble() / 1000 / (duration.toSeconds().toDouble() / 3600)
}
