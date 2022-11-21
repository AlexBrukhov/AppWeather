package alexbrukhov.appweather.data.models

import com.squareup.moshi.Json

data class ForecastResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)

data class Current(
    val condition: Condition,
    @Json(name = "temp_c")
    val temp_c: Double,
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avgtemp_c: Double,
    val condition: Condition
)

data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Hour(
    val condition: Condition,
    val temp_c: Double,
    val time: String
)

data class Location(
    val country: String,
    val lon: Double,
    val name: String,
    val region: String,
    var localtime: String
)