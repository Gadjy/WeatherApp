package com.example.weatherapp.presentation.uistates

import com.example.weatherapp.domain.model.WeatherInfo

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val data: WeatherInfo) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
