package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.WeatherApiResponse
import com.example.weatherapp.domain.model.WeatherInfo

fun WeatherApiResponse.toDomain(): WeatherInfo = WeatherInfo(
    city = location.name,
    temperature = current.temp_c,
    description = current.condition.text,
    icon = "https:${current.condition.icon}"
)

