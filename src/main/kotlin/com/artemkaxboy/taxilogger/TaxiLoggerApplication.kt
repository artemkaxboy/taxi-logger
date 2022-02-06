package com.artemkaxboy.taxilogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaxiLoggerApplication

fun main(args: Array<String>) {
    runApplication<TaxiLoggerApplication>(*args)
}
