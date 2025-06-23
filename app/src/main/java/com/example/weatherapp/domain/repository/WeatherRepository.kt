package com.example.weatherapp.domain.repository


import com.example.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherByLocation(q: String): WeatherInfo
}
