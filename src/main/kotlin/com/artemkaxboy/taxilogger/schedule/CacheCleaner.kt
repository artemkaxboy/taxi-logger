package com.artemkaxboy.taxilogger.schedule

import mu.KotlinLogging
import org.springframework.cache.annotation.CacheEvict
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
@EnableScheduling
class CacheCleaner {

    @Scheduled(fixedRateString = "#{@cacheCustomizer.yandexApiProperties.cacheTtl.toMillis()}")
    @CacheEvict("ride", allEntries = true)
    fun evictAll() {
        logger.debug { "cache cleaned" }
    }
}
