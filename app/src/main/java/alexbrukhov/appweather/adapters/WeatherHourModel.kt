package alexbrukhov.appweather.adapters

import alexbrukhov.appweather.data.models.Condition

data class WeatherHourModel(
    val city: String,
    val time: String,
    val condition: Condition,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String
)
