package com.example.weatherapp.domain.model

data class WeatherInfo(
    val temperature: Double,
    val description: String,
    val icon: String,
    val city: String
)
