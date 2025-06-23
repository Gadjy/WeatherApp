package com.example.weatherapp.data.remote
import kotlinx.serialization.Serializable


@Serializable
data class WeatherApiResponse(
    val location: LocationDto,
    val current: CurrentDto
)

@Serializable
data class LocationDto(
    val name: String,
    //val country: String
)

@Serializable
data class CurrentDto(
    val temp_c: Double,
    val condition: ConditionDto
)

@Serializable
data class ConditionDto(
    val text: String,
    val icon: String
)

