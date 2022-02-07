package com.artemkaxboy.taxilogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.artemkaxboy.taxilogger.configuration.properties"])
class TaxiLoggerApplication

fun main(args: Array<String>) {
    runApplication<TaxiLoggerApplication>(*args)
}
