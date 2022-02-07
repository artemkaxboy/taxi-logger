package com.artemkaxboy.taxilogger.configuration

import com.artemkaxboy.taxilogger.configuration.properties.YandexApiProperties
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

@Component
@EnableCaching
class CacheCustomizer(

    @Suppress("unused") // It is being used in CacheCleaner.kt schedule expression
    val yandexApiProperties: YandexApiProperties,
) : CacheManagerCustomizer<ConcurrentMapCacheManager> {

    override fun customize(cacheManager: ConcurrentMapCacheManager) {
        cacheManager.setCacheNames(listOf("ride"))
    }
}
