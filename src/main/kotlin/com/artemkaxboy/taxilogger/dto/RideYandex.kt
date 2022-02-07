package com.artemkaxboy.taxilogger.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RideYandexDto(

    val options: Collection<RideOptionsYandexDto>,

    /** 3-letter currency code */
    val currency: String,

    /** Distance in meters */
    val distance: Double = Double.NaN,

    /** Time in seconds */
    val time: Double = Double.NaN,
)

data class RideOptionsYandexDto(
    val price: Double,

    @JsonProperty("min_price")
    val minPrice: Double?,

    @JsonProperty("waiting_time")
    val waitingTime: Double,

    @JsonProperty("class_name")
    val className: String,

    @JsonProperty("class_level")
    val classLevel: Int,
)

/*
{
    "options": [
        {
            "price": 333.0,
            "min_price": 89.0,
            "waiting_time": 138.0,
            "class_name": "econom",
            "class_text": "Эконом",
            "class_level": 50,
            "price_text": "~333 руб."
        }
    ],
    "currency": "RUB",
    "distance": 14295.645987153064,
    "time": 2567.0,
    "time_text": "45 мин"
}
 */
