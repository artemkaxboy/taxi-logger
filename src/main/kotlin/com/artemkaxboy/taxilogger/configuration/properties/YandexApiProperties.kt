package com.artemkaxboy.taxilogger.configuration.properties

import com.artemkaxboy.taxilogger.entity.Position
import com.artemkaxboy.taxilogger.entity.Route
import org.hibernate.validator.constraints.time.DurationMin
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import org.springframework.web.util.UriComponentsBuilder
import java.time.Duration
import javax.validation.constraints.NotBlank

const val CLIENT_ID_PARAM_NAME = "clid"
const val COORDINATE_PARAM_NAME = "rll"

/**
 * Properties of yandex taxi API: https://yandex.ru/dev/taxi/doc/dg/concepts/trip-info.html#trip-info
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "yandex.api")
@Validated
class YandexApiProperties(

    val url: String = "https://taxi-routeinfo.taxi.yandex.net/taxi_info",

    val keyHeader: String = "YaTaxi-Api-Key",

    /** API key */
    @field:NotBlank(message = "You must provide api-key")
    val key: String,

    /** Client ID */
    @field:NotBlank(message = "You must provide clid")
    private val clid: String,

    /** API request cache time to live */
    @Suppress("unused") // It is being used in CacheCleaner.kt schedule expression
    @field:DurationMin(seconds = 30)
    val cacheTtl: Duration = Duration.ofMinutes(5)
) {

    fun getUri() = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam(CLIENT_ID_PARAM_NAME, "{$CLIENT_ID_PARAM_NAME}")
        .queryParam(COORDINATE_PARAM_NAME, "{$COORDINATE_PARAM_NAME}")
        .encode()
        .toUriString()

    fun getParamsMap(route: Route) = mapOf(
        CLIENT_ID_PARAM_NAME to clid,
        COORDINATE_PARAM_NAME to getCoordinateParamValue(route.from, route.to),
    )
}

fun getCoordinateParamValue(from: Position, to: Position) =
    "${from.longitude},${from.latitude}~${to.longitude},${to.latitude}"
