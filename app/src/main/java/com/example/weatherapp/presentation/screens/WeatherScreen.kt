package com.example.weatherapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.components.ErrorMessage
import com.example.weatherapp.presentation.components.LoadingIndicator
import com.example.weatherapp.presentation.components.WeatherCard
import com.example.weatherapp.presentation.uistates.WeatherUiState
import com.example.weatherapp.presentation.viewmodels.WeatherViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var city by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = {Text(text = stringResource(R.string.app_name))}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Введите город") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.loadWeather(city) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)
            ) {
                Text("Показать погоду")
            }

            Spacer(modifier = Modifier.height(24.dp))

            when (uiState) {
                is WeatherUiState.Loading -> LoadingIndicator()
                is WeatherUiState.Success -> WeatherCard((uiState as WeatherUiState.Success).data)
                is WeatherUiState.Error -> ErrorMessage((uiState as WeatherUiState.Error).message)
            }
        }
    }
}
