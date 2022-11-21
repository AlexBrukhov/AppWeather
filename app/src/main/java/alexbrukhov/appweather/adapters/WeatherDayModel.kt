package alexbrukhov.appweather.adapters

import alexbrukhov.appweather.data.models.Condition
import alexbrukhov.appweather.data.models.Hour

data class WeatherDayModel(
    val city: String,
    val time: String,
    val condition: Condition,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    val hours: List<Hour>
)
