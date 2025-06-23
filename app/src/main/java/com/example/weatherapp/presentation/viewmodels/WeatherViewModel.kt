package com.example.weatherapp.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.location.LocationProvider
import com.example.weatherapp.domain.usecases.GetWeatherUseCase
import com.example.weatherapp.presentation.uistates.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    //private val locationProvider: LocationProvider
) : ViewModel() {

    var uiState by mutableStateOf<WeatherUiState>(WeatherUiState.Loading)
        private set

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            uiState = WeatherUiState.Loading
            try {
                //val location = locationProvider.getCurrentLocation()
                val weather = getWeatherUseCase("London")
                uiState = WeatherUiState.Success(weather)
            } catch (e: Exception) {
                Log.e("WeatherApp", "Ошибка при загрузке погоды: ${e.message}", e)
                uiState = WeatherUiState.Error(e.message ?: "Ошибка загрузки погоды")
            }
        }
    }
}
