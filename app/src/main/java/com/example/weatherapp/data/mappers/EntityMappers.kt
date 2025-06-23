package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.domain.model.WeatherInfo

fun WeatherInfo.toEntity(): WeatherEntity = WeatherEntity(
    city = city,
    temperature = temperature,
    description = description,
    icon = icon,
    timestamp = System.currentTimeMillis()
)


fun WeatherEntity.toDomain(): WeatherInfo = WeatherInfo(
    temperature = temperature,
    description = description,
    icon = icon,
    city = city
)

