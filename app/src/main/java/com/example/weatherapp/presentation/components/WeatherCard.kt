package com.example.weatherapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.domain.model.WeatherInfo

@Composable
fun WeatherCard(info: WeatherInfo) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Город: ${info.city}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Температура: ${info.temperature}°C", style = MaterialTheme.typography.titleLarge)
            Text(text = info.description.capitalize(), style = MaterialTheme.typography.bodyLarge)

            AsyncImage(
                model = info.icon,
                contentDescription = "Погодная иконка",
                modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}
