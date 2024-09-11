package com.northcoders.record_shop.controller;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CaffeineCache getAllAlbumCache() {
        return new CaffeineCache("getAllAlbumCache",
                Caffeine.newBuilder()
                        .expireAfterAccess(20, TimeUnit.SECONDS)
                        .build());
    }

    @Bean
    public CaffeineCache getAlbumCache() {
        return new CaffeineCache("getAlbumCache",
                Caffeine.newBuilder()
                        .expireAfterAccess(5, TimeUnit.SECONDS)
                        .build());
    }

}
