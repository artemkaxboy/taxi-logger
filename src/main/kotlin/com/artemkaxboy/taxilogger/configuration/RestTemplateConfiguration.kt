package com.artemkaxboy.taxilogger.configuration

import com.artemkaxboy.taxilogger.configuration.properties.YandexApiProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration(

    private val yandexApiProperties: YandexApiProperties,
) {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate =
        builder.defaultHeader(yandexApiProperties.keyHeader, yandexApiProperties.key)
            .interceptors(JsonMimeReqInterceptor())
            .build()
}

class JsonMimeReqInterceptor : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {

        request.headers.accept = listOf(MediaType.APPLICATION_JSON)
        return execution.execute(request, body)
    }
}
