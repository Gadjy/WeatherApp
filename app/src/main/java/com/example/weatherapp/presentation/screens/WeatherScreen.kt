package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.util.copy
import com.example.weatherapp.presentation.components.ErrorMessage
import com.example.weatherapp.presentation.components.LoadingIndicator
import com.example.weatherapp.presentation.components.WeatherCard
import com.example.weatherapp.presentation.uistates.WeatherUiState
import com.example.weatherapp.presentation.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val state = viewModel.uiState
    var city = ""

    Column {
        OutlinedTextField(value = city, onValueChange = {  } )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is WeatherUiState.Loading -> LoadingIndicator()
            is WeatherUiState.Success -> WeatherCard(state.data)
            is WeatherUiState.Error -> ErrorMessage(state.message)
        }
    }
}
