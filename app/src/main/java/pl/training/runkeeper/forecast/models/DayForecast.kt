package pl.training.runkeeper.forecast.models

import java.util.*

data class DayForecast(val id: Long, val date: Date, val description: String, val minTemperature: Int, val maxTemperature: Int, val iconUrl: String)