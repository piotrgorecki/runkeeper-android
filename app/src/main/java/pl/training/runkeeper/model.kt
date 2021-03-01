package pl.training.runkeeper

import java.util.*

data class Forecast(val id: Long, val cityName: String, val data: List<DayForecast>)

data class DayForecast(val id: Long, val date: Date, val description: String, val minTemperature: Int, val maxTemperature: Int, val iconUrl: String)